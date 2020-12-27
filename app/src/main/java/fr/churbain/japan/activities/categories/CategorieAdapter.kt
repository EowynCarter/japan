package fr.churbain.japan.activities.categories

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.churbain.japan.*
import fr.churbain.japan.model.Categorie


class CategorieAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var elements = emptyList<Categorie>()

    var multiSelection = false

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
            val intent = Intent(activity, DisplayCatActivity::class.java)
            intent.putExtra(DISPLAY_CAT, cat)
            activity.startActivityForResult(intent, displayCatRequestCode)
        }

        holder.itemView.setOnLongClickListener { v ->

            val activity = v.context as Activity
            val pos = holder.adapterPosition
            val cat = elements[pos]
            val intent = Intent(activity, ModifierCategorieActivity::class.java)
            intent.putExtra(MODIF_CAT, cat)
            activity.startActivityForResult(intent, modifierCatRequestCode)
            true
        }

    }

    private fun selectCat(v: View?) {
        v?.isSelected = true
        v?.setBackgroundColor(Color.BLACK)
    }

    internal fun setElement(words: List<Categorie>) {
        this.elements = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = elements.size
}