package fr.churbain.japan.activities.categories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.churbain.japan.*
import fr.churbain.japan.activities.element.AjoutElementActivity
import fr.churbain.japan.activities.element.ElementAdapter
import fr.churbain.japan.database.ElementViewModel
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element

class DisplayCatActivity : AppCompatActivity() {

    private lateinit var elementViewModel: ElementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_cat)

        val categorie: Categorie = intent.extras?.getParcelable(DISPLAY_CAT)!!

        val recyclerView = findViewById<RecyclerView>(R.id.listElement)
        val adapter = ElementAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val catRecyclerView = findViewById<RecyclerView>(R.id.listCat)
        val catAdapter = CategorieAdapter(this@DisplayCatActivity)
        catRecyclerView.adapter = catAdapter
        catRecyclerView.layoutManager = LinearLayoutManager(this)

        elementViewModel = ElementViewModel(this.application)

        elementViewModel.getElementForCat(categorie.id!!).observe(this, Observer { cat ->
            cat?.let { adapter.setElement(it) }
        })

        elementViewModel.getSousCategorie(categorie.id!!).observe(this, Observer { cat ->
            cat?.let { catAdapter.setElement(it) }
            if (cat.size == 0) {
                catRecyclerView.visibility = View.GONE
            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@DisplayCatActivity, AjoutElementActivity::class.java)
            intent.putExtra(AJOUT_ELT, categorie)
            startActivityForResult(intent, ajoutElementRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ajoutElementRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Element>(RETOUR_AJOUT_ELT)?.let {
                elementViewModel.insert(it)
            }
        }

        if (requestCode == modifierElementRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Element>(RETOUR_AJOUT_ELT)?.let {
                elementViewModel.update(it)
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


    }


}