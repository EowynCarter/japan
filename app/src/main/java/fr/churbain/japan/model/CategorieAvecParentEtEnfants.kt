package fr.churbain.japan.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategorieAvecParentEtEnfants(

    @Embedded
    val categorie: Categorie,

    @Relation(
        parentColumn = "id",
        entityColumn = "parent"
    )
    val parentCat: Categorie
)
