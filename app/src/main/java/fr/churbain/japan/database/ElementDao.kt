package fr.churbain.japan.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementAvecCategorie


@Dao
interface ElementDao {

    @Insert
    fun insertAll(vararg elements: Element)

    @Update
    fun update(element: Element)

    @Delete
    fun delete(element: Element)

    @Query("SELECT * from element ORDER BY id ASC")
    fun getAll(): LiveData<List<Element>>

    @Query("SELECT * from element ORDER BY id ASC")
    fun getAllAvecCat(): LiveData<List<ElementAvecCategorie>>

    @Query("SELECT * from element where categorie = :idCat ORDER BY id ASC")
    fun getAllForCat(idCat: Long): LiveData<List<ElementAvecCategorie>>

    @Query("SELECT * from element ORDER BY id ASC")
    fun getList(): List<Element>

}