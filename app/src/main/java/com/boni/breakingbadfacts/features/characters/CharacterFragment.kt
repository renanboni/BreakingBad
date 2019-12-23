package com.boni.breakingbadfacts.features.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.features.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterFragment: HasViewModel<CharacterViewModel>, BaseFragment() {

    private val characterViewModel by viewModel<CharacterViewModel>()

    override val viewModel: CharacterViewModel
        get() = characterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_characters,
            container,
            false
        )
    }

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when (viewState) {
            is CharacterViewModel.CharactersViewState.CharactersState -> {
                characters.adapter = CharacterAdapter(viewState.characterList)
            }
        }
    }
}