package com.boni.breakingbadfacts.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.features.model.Character
import com.bumptech.glide.Glide

class HomeAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<HomeAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.character_item_list,
            parent,
            false
        )

        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characterList.count()

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val characterImage by lazy { view.findViewById<ImageView>(R.id.img) }

        fun bind(character: Character) {
            Glide.with(view)
                .load(character.img)
                .into(characterImage)
        }
    }
}