package com.boni.breakingbadfacts.features.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.features.model.Episode

class EpisodesAdapter(private val episodes: List<Episode>) :
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

    inner class EpisodesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val season by lazy { view.findViewById<TextView>(R.id.season) }
        private val title by lazy { view.findViewById<TextView>(R.id.title) }

        fun bind(episode: Episode) {
            season.text = episode.season
            title.text = episode.title
        }
    }
}