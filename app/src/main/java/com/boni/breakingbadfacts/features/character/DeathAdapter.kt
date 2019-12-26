package com.boni.breakingbadfacts.features.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.features.model.Death

class DeathAdapter (private val deathList: List<Death>): RecyclerView.Adapter<DeathAdapter.DeathViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeathViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.execution_item_list,
            parent,
            false
        )

        return DeathViewHolder(view)
    }

    override fun getItemCount() = deathList.count()

    override fun onBindViewHolder(holder: DeathViewHolder, position: Int) {
        holder.bind(deathList[position])
    }

    inner class DeathViewHolder (view: View): RecyclerView.ViewHolder(view) {

        private val victim by lazy { view.findViewById<TextView>(R.id.victim) }
        private val cause by lazy { view.findViewById<TextView>(R.id.cause) }
        private val lastWords by lazy { view.findViewById<TextView>(R.id.last_words) }

        fun bind(death: Death) {
            victim.text = death.death
            cause.text = death.cause
            lastWords.text = death.lastWords
        }
    }
}