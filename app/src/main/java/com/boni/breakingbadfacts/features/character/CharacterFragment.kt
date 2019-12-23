package com.boni.breakingbadfacts.features.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.utils.setVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterFragment : HasViewModel<CharacterViewModel>, BaseFragment() {

    private val characterViewModel by viewModel<CharacterViewModel>()

    override val viewModel: CharacterViewModel
        get() = characterViewModel

    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_character,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        portrayed.text = args.character.portrayed
        nickname.text = args.character.nickname
        birthday.text = args.character.birthday
        deceased.setVisible(args.character.status != Character.ALIVE)

        Glide.with(this)
            .load(args.character.img)
            .into(picture)
    }
}