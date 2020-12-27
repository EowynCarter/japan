package fr.churbain.japan.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.CategorieAvecParentEtEnfants


@Dao
interface CategorieDao {

    @Insert
    fun insertAll(vararg categorie: Categorie)

    @Update
    fun update(categorie: Categorie)

    @Delete
    fun delete(categorie: Categorie)

    @Query("SELECT * from categorie ORDER BY id ASC")
    fun getAll(): LiveData<List<Categorie>>

    @Query("SELECT * from categorie where parent is null ORDER BY id ASC")
    fun getAllWithoutParent(): LiveData<List<Categorie>>

    @Query("SELECT * from categorie ORDER BY id ASC")
    fun getAllList(): List<Categorie>

    @Transaction
    @Query("SELECT * FROM categorie")
    fun getCategorieAvecParentEtEnfants(): List<CategorieAvecParentEtEnfants>

    @Query("SELECT * from categorie where parent = :catId ORDER BY id ASC")
    fun getSousCategorie(catId: Long): LiveData<List<Categorie>>

    @Query("SELECT * from categorie where id = :catId")
    fun getCatForId(catId: Long): LiveData<Categorie>
}