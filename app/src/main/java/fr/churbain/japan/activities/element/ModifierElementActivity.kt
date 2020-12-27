package fr.churbain.japan.activities.element

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import fr.churbain.japan.*
import fr.churbain.japan.activities.categories.CategorieSelectActivity
import fr.churbain.japan.model.Categorie
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementAvecCategorie
import fr.churbain.japan.model.ElementType


class ModifierElementActivity : AppCompatActivity() {

    private lateinit var notesView: EditText
    private lateinit var kanjiView: EditText
    private lateinit var kanaview: EditText
    private lateinit var francaisView: EditText
    private lateinit var romanjiView: EditText
    private lateinit var noteView: EditText
    private lateinit var type: ElementType
    private lateinit var categorie: TextView
    private lateinit var element: Element
    private lateinit var elementAvecCat: ElementAvecCategorie


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifier_element)

        elementAvecCat = intent.extras?.getParcelable(RETOUR_MODIF_ELT)!!
        element = elementAvecCat.elementType

        kanjiView = findViewById(R.id.kanji)
        kanaview = findViewById(R.id.kana)
        francaisView = findViewById(R.id.francais)
        romanjiView = findViewById(R.id.romanji)
        noteView = findViewById(R.id.note)
        notesView = findViewById(R.id.notes)
        categorie = findViewById(R.id.categorie)

        type = element?.type!!
        kanjiView.setText(element?.kanji ?: "")
        kanaview.setText(element?.kana ?: "")
        francaisView.setText(element?.francais ?: "")
        romanjiView.setText(element?.romanji ?: "")
        notesView.setText(element?.notes ?: "")
        noteView.setText(element?.note.toString())

        if (elementAvecCat.categorie != null) {
            categorie.setText(elementAvecCat.categorie!!.label!!.toString())
        } else {
            categorie.setText(R.string.aucune_cat)
        }


        val button_select_cat = findViewById<Button>(R.id.select_cat)

        button_select_cat.setOnClickListener {
            val intent = Intent(this@ModifierElementActivity, CategorieSelectActivity::class.java)
            startActivityForResult(intent, selectCategorieRequestCode)
        }

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            sauvgarder()
        }

        val spinner: Spinner = findViewById(R.id.type_spinner)

        spinner.setAdapter(
            ArrayAdapter<ElementType>(
                this,
                android.R.layout.simple_spinner_item,
                ElementType.values()

            )
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        spinner.setSelection(ElementType.values().indexOf(type));
    }

    private fun sauvgarder() {
        val replyIntent = Intent()

        element.kanji = kanjiView.text.toString()
        element.kana = kanaview.text.toString()
        element.francais = francaisView.text.toString()
        element.notes = notesView.text.toString()
        element.romanji = romanjiView.text.toString()
        element.note = noteView.text.toString().toInt()
        element.type = type;

        replyIntent.putExtra(RETOUR_AJOUT_ELT, element)
        setResult(RESULT_OK, replyIntent)

        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == selectCategorieRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Categorie>(RETOUR_SELECT_CAT)?.let {
                elementAvecCat.categorie = it
                element.categorie = it.id
                categorie.setText(it.label!!.toString())
            }


        }

    }
}