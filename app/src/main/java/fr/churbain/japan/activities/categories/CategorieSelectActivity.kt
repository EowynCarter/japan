package fr.churbain.japan.activities.categories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.churbain.japan.R
import fr.churbain.japan.ajoutCatRequestCode
import fr.churbain.japan.database.ElementViewModel
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element

class CategorieSelectActivity : AppCompatActivity() {

    private lateinit var elementViewModel: ElementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_select_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.categoriesReView)
        val adapter = SelectCategorieAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        elementViewModel = ElementViewModel(this.application)

        elementViewModel.allCategorie.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setElement(it) }
        })


    }


}