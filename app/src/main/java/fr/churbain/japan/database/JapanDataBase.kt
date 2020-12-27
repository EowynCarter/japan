package fr.churbain.japan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Element::class, Categorie::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JapanDataBase : RoomDatabase() {

    abstract fun elementDao(): ElementDao

    abstract fun categorieDao(): CategorieDao

    companion object {
        @Volatile
        private var INSTANCE: JapanDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): JapanDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JapanDataBase::class.java,
                    "japan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}