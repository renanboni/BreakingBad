package com.boni.characters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.features.model.Character
import com.boni.characters.R
import com.bumptech.glide.Glide

class CharactersAdapter(
    private val characterList: List<Character>,
    private val listener: (Character) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

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

    inner class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val characterPicture by lazy { view.findViewById<ImageView>(R.id.picture) }
        private val characterName by lazy { view.findViewById<TextView>(R.id.name) }
        private val characterStatus by lazy { view.findViewById<TextView>(R.id.status) }
        private val characterOcupation by lazy { view.findViewById<TextView>(R.id.ocupation) }

        fun bind(character: Character) {
            Glide.with(view)
                .load(character.img)
                .into(characterPicture)

            characterName.text = character.name
            characterStatus.text = character.status
            characterStatus.setTextColor(Character.getStatusColor(character.status))
            characterOcupation.text = character.occupation.joinToString(",")

            view.setOnClickListener {
                listener(character)
            }
        }
    }
}