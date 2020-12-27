package fr.churbain.japan.activities.categories

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.churbain.japan.R
import fr.churbain.japan.RETOUR_SELECT_CAT
import fr.churbain.japan.model.Categorie


class SelectCategorieAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SelectCategorieAdapter.CategorieViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var elements = emptyList<Categorie>()

    inner class CategorieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label: TextView = itemView.findViewById(R.id.label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
        val itemView = inflater.inflate(R.layout.categorie_item, parent, false)
        return CategorieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
        val current = elements[position]
        holder.label.setText(current.label)

        holder.itemView.setOnClickListener { v ->
            val activity = v.context as Activity
            val pos = holder.adapterPosition
            val cat = elements[pos]
            val replyIntent = Intent()
            replyIntent.putExtra(RETOUR_SELECT_CAT, cat)
            activity.setResult(AppCompatActivity.RESULT_OK, replyIntent)
            activity.finish()
        }
    }

    internal fun setElement(words: List<Categorie>) {
        this.elements = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = elements.size
}