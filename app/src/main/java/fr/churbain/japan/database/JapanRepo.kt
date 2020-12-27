package fr.churbain.japan.database

import androidx.lifecycle.LiveData
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementAvecCategorie

class JapanRepo(private val elementDao: ElementDao, private val categorieDao: CategorieDao) {

    suspend fun insert(element: Element?) {
        elementDao.insertAll(element!!);
    }

    suspend fun update(element: Element) {
        elementDao.update(element);
    }

    suspend fun insert(categorie: Categorie?) {
        categorieDao.insertAll(categorie!!);
    }

    suspend fun update(categorie: Categorie) {
        categorieDao.update(categorie);
    }

    fun getElementForCat(catId: Long): LiveData<List<ElementAvecCategorie>> {
        return elementDao.getAllForCat(catId)
    }

    fun getSousCategorie(catId: Long): LiveData<List<Categorie>> {
        return categorieDao.getSousCategorie(catId)
    }

    fun getCatForId(catId: Long): LiveData<Categorie> {
        return categorieDao.getCatForId(catId)
    }

    val allElement: LiveData<List<Element>> = elementDao.getAll()

    val allCategorieWithoutParent: LiveData<List<Categorie>> = categorieDao.getAllWithoutParent()

    val elementAvecCategorie: LiveData<List<ElementAvecCategorie>> = elementDao.getAllAvecCat()

    val allCategorie: LiveData<List<Categorie>> = categorieDao.getAll()

}