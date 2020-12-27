package fr.churbain.japan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Categorie(

    @PrimaryKey(autoGenerate = true) val id: Long?,

    @ColumnInfo(name = "label") var label: String?,

    @ColumnInfo(name = "parent") var parent: Long?

) : Parcelable