package fr.churbain.japan.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ElementAvecCategorie(

    @Embedded
    val elementType: Element,

    @Relation(
        parentColumn = "categorie",
        entityColumn = "id"
    )
    var categorie: Categorie?
) : Parcelable
