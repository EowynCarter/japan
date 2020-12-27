package fr.churbain.japan.activities.element

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.churbain.japan.RETOUR_MODIF_ELT
import fr.churbain.japan.R
import fr.churbain.japan.model.Element
import fr.churbain.japan.model.ElementAvecCategorie
import fr.churbain.japan.modifierElementRequestCode


class ElementAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var elements = emptyList<ElementAvecCategorie>()

    inner class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kana: TextView = itemView.findViewById(R.id.kana)
        val romanji: TextView = itemView.findViewById(R.id.romanji)
        val francais: TextView = itemView.findViewById(R.id.francais)
        val kanji: TextView = itemView.findViewById(R.id.kanji)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView = inflater.inflate(R.layout.element_item, parent, false)
        return ElementViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val current = elements[position]
        holder.kana.text = "kana : " + current.elementType.kana
        holder.romanji.text = "romanji : " + current.elementType.romanji
        holder.francais.text = "francais : " + current.elementType.francais
        holder.kanji.text = "kanji : " + current.elementType.kanji

        holder.itemView.setOnClickListener { v ->
            val activity = v.context as Activity
            val pos = holder.adapterPosition
            val ele = elements[pos]
            val intent = Intent(activity, ModifierElementActivity::class.java)
            intent.putExtra(RETOUR_MODIF_ELT, ele)
            activity.startActivityForResult(intent, modifierElementRequestCode)
        }

    }

    internal fun setElement(words: List<ElementAvecCategorie>) {
        this.elements = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = elements.size
}