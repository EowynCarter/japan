package fr.churbain.japan.activities.categories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import fr.churbain.japan.*
import fr.churbain.japan.database.ElementViewModel
import fr.churbain.japan.model.Categorie


class ModifierCategorieActivity : AppCompatActivity() {

    private lateinit var nom: EditText

    private lateinit var parentLabel: AppCompatTextView

    private lateinit var categorie: Categorie

    private lateinit var parent: Categorie

    private lateinit var elementViewModel: ElementViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifier_categorie)
        categorie = intent.extras?.getParcelable(MODIF_CAT)!!

        elementViewModel = ElementViewModel(this.application)
        parentLabel = findViewById(R.id.parent)
        nom = findViewById(R.id.nom)

        if (categorie.parent != null) {
            elementViewModel.getCatForId(categorie.parent!!).observe(this, Observer { cat ->
                cat?.let { parent = it }
                parentLabel.setText(parent.label)

            })
        }

        nom.setText(categorie.label)

        val button_select_cat = findViewById<Button>(R.id.select_cat)

        button_select_cat.setOnClickListener {
            val intent = Intent(this@ModifierCategorieActivity, CategorieSelectActivity::class.java)
            startActivityForResult(intent, selectCategorieRequestCode)
        }

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            sauvgarder()
        }

    }

    private fun sauvgarder() {
        val replyIntent = Intent()

        val nom = nom.text.toString()
        categorie.label = nom
        categorie.parent = parent.id
        replyIntent.putExtra(RETOUR_MODIF_CAT, categorie)
        setResult(RESULT_OK, replyIntent)

        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == selectCategorieRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Categorie>(RETOUR_SELECT_CAT)?.let {
                parent = it
                parentLabel.setText(it.label!!.toString())
            }


        }

    }

}