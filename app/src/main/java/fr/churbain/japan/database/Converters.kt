package fr.churbain.japan.database

import androidx.room.TypeConverter
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementType

class Converters {

    @TypeConverter
    fun ElementTypeToString(element: ElementType?): String? {
        return element?.name
    }

    @TypeConverter
    fun StringToElementType(element: String?): ElementType? {
        return ElementType.valueOf(element!!)
    }

}