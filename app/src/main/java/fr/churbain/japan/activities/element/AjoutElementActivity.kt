package fr.churbain.japan.activities.element

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import fr.churbain.japan.AJOUT_ELT
import fr.churbain.japan.R
import fr.churbain.japan.RETOUR_AJOUT_ELT
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementType


class AjoutElementActivity : AppCompatActivity() {

    private lateinit var notesView: EditText
    private lateinit var kanjiView: EditText
    private lateinit var kanaview: EditText
    private lateinit var francaisView: EditText
    private lateinit var romanji: EditText
    private lateinit var type: ElementType
    private lateinit var categorie: Categorie
    private val catsLib: MutableMap<String, Categorie> = mutableMapOf<String, Categorie>()


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_element)

        val cats = mutableListOf<Categorie>()

        categorie = intent.extras?.getParcelable(AJOUT_ELT)!!

        cats.forEach { catsLib.put(it.label!!, it) }

        kanjiView = findViewById(R.id.kanji)
        kanaview = findViewById(R.id.kana)
        francaisView = findViewById(R.id.francais)
        romanji = findViewById(R.id.romanji)
        notesView = findViewById(R.id.notes)

        type = ElementType.values().get(0)

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            sauvgarder()
        }

        val typeSpinner: Spinner = findViewById(R.id.type_spinner)

        typeSpinner.setAdapter(
            ArrayAdapter<ElementType>(
                this,
                android.R.layout.simple_spinner_item,
                ElementType.values()
            )
        )

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val value = view as AppCompatTextView
                type = ElementType.valueOf(value.text.toString())

                val layout = kanjiView.parent as LinearLayout
                layout.isVisible = true

                if (type == ElementType.KANA) {
                    layout.isVisible = false
                }
            }
        }
    }


    private fun sauvgarder() {
        val replyIntent = Intent()

        val kanji = kanjiView.text.toString()
        val kana = kanaview.text.toString()
        val francais = francaisView.text.toString()
        val notes = notesView.text.toString()
        val romanji = romanji.text.toString()

        val elt = Element(null, type, romanji, kanji, kana, francais, notes, 0, categorie.id)

        replyIntent.putExtra(RETOUR_AJOUT_ELT, elt)
        setResult(RESULT_OK, replyIntent)
        finish()
    }

}