package fr.churbain.japan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Element(

    @PrimaryKey(autoGenerate = true) val id: Int?,

    @ColumnInfo(name = "type") var type: ElementType?,

    @ColumnInfo(name = "romanji") var romanji: String?,

    @ColumnInfo(name = "kanji") var kanji: String?,

    @ColumnInfo(name = "kana") var kana: String?,

    @ColumnInfo(name = "francais") var francais: String?,

    @ColumnInfo(name = "notes") var notes: String?,

    @ColumnInfo(name = "note") var note: Int?,

    @ColumnInfo(name = "categorie") var categorie: Long?

) : Parcelable