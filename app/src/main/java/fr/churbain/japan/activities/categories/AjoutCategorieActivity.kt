package fr.churbain.japan.activities.categories

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import fr.churbain.japan.R
import fr.churbain.japan.RETOUR_AJOUT_CAT
import fr.churbain.japan.model.Categorie


class AjoutCategorieActivity : AppCompatActivity() {

    private lateinit var nom: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_categorie)

        nom = findViewById(R.id.nom)

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            sauvgarder()
        }

    }

    private fun sauvgarder() {
        val replyIntent = Intent()

        val nom = nom.text.toString()
        val cat = Categorie(null, nom, null)

        replyIntent.putExtra(RETOUR_AJOUT_CAT, cat)
        setResult(RESULT_OK, replyIntent)
        finish()
    }

}