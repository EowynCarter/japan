package fr.churbain.japan.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementAvecCategorie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JapanRepo

    val allElement: LiveData<List<Element>>

    val allCategorie: LiveData<List<Categorie>>

    val allCategorieWithoutParent: LiveData<List<Categorie>>

    val allAlementWithCat: LiveData<List<ElementAvecCategorie>>

    init {
        val elementDao = JapanDataBase.getDatabase(application, viewModelScope).elementDao()
        val categorieDao = JapanDataBase.getDatabase(application, viewModelScope).categorieDao()

        repository = JapanRepo(elementDao, categorieDao)
        allElement = repository.allElement
        allCategorie = repository.allCategorie
        allAlementWithCat = repository.elementAvecCategorie
        allCategorieWithoutParent = repository.allCategorieWithoutParent

    }

    fun insert(element: Element) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(element)
    }

    fun update(element: Element) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(element)
    }

    fun insert(categorie: Categorie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(categorie)
    }

    fun update(categorie: Categorie) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(categorie)
    }

    fun getElementForCat(catId: Long): LiveData<List<ElementAvecCategorie>> {
        return repository.getElementForCat(catId)
    }

    fun getSousCategorie(catId: Long): LiveData<List<Categorie>> {
        return repository.getSousCategorie(catId)
    }

    fun getCatForId(catId: Long): LiveData<Categorie> {
        return repository.getCatForId(catId)
    }


}