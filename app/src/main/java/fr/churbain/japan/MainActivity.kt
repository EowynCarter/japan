package fr.churbain.japan

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
import fr.churbain.japan.activities.categories.AjoutCategorieActivity
import fr.churbain.japan.activities.categories.CategorieAdapter
import fr.churbain.japan.database.ElementViewModel
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element

class MainActivity : AppCompatActivity() {

    private lateinit var elementViewModel: ElementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.listCat)
        val adapter = CategorieAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        elementViewModel = ElementViewModel(this.application)

        elementViewModel.allCategorieWithoutParent.observe(this, Observer { cat ->
            cat?.let { adapter.setElement(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AjoutCategorieActivity::class.java)
            startActivityForResult(intent, ajoutCatRequestCode)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_delete -> {
                val intent = Intent(this@MainActivity, AjoutCategorieActivity::class.java)
                startActivityForResult(intent, ajoutCatRequestCode)
                true
            }

            else -> true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ajoutElementRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Element>(RETOUR_AJOUT_ELT)?.let {
                elementViewModel.insert(it)
            }
        }

        if (requestCode == ajoutCatRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Categorie>(RETOUR_AJOUT_CAT)?.let {
                elementViewModel.insert(it)
            }
        }

        if (requestCode == modifierCatRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Categorie>(RETOUR_MODIF_CAT)?.let {
                elementViewModel.update(it)
            }
        }

        if (requestCode == modifierElementRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Element>(RETOUR_AJOUT_ELT)?.let {
                elementViewModel.update(it)
            }
        }

    }


}