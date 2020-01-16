package com.boni.episodes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.features.model.Episode
import com.boni.breakingbadfacts.ui.CheckView
import com.boni.episodes.R

class EpisodesAdapter(
    private val episodes: List<Episode>,
    private val listener: (Episode) -> Unit
) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.episode_item_list,
            parent,
            false
        )

        return EpisodesViewHolder(view)
    }

    override fun getItemCount() = episodes.count()

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemId(position: Int): Long {
        return episodes[position].id.toLong()
    }

    fun updateItem(episode: Episode) {
        val index = episodes.indexOf(episode)
        notifyItemChanged(index)
    }

    inner class EpisodesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val season by lazy { view.findViewById<TextView>(R.id.season) }
        private val title by lazy { view.findViewById<TextView>(R.id.title) }
        private val check by lazy { view.findViewById<CheckView>(R.id.check) }

        fun bind(episode: Episode) {
            season.text = episode.episode
            title.text = episode.title
            check.setChecked(episode.isChecked)

            view.setOnClickListener {
                listener(episode)
            }
        }
    }
}