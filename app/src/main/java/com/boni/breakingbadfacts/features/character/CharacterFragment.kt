package com.boni.breakingbadfacts.features.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.utils.LineItemDecoration
import com.boni.breakingbadfacts.utils.MarginItemDecoration
import com.boni.breakingbadfacts.utils.addMarginBetweenItems
import com.boni.breakingbadfacts.utils.addSeparatorBetweenItems
import com.boni.breakingbadfacts.utils.setVisible
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
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
        viewModel.getQuotes(args.character.name)
        setupViews()
    }

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when (viewState) {
            is CharacterViewModel.CharacterState.Quotes -> setQuoteText()
            is CharacterViewModel.CharacterState.Deaths -> renderExecutionList(viewState)
        }
    }

    private fun renderExecutionList(viewState: CharacterViewModel.CharacterState.Deaths) {
        executions.adapter = DeathAdapter(viewState.deaths)
        executions.addMarginBetweenItems()
        executions.addSeparatorBetweenItems()
        executionLabel.setVisible(viewState.deaths.isNotEmpty())
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        portrayed.text = args.character.portrayed
        nickname.text = args.character.nickname
        birthday.text = args.character.birthday
        deceased.setVisible(args.character.status != Character.ALIVE)

        Glide.with(this)
            .load(args.character.img)
            .into(picture)

        quote.setOnClickListener { setQuoteText() }

        args.character.appearance.forEach {
            val chip = Chip(chips.context)
            chip.text = "Season $it"
            chip.isClickable = true
            chip.isCheckable = false
            chip.setOnClickListener { _ ->
                val action =
                    CharacterFragmentDirections.actionCharacterFragmentToEpisodesFragment(it - 1)
                findNavController().navigate(action)
            }
            chips.addView(chip)
        }

        chips.isSingleSelection = true
    }

    private fun setQuoteText() {
        val qt = viewModel.getNextQuote()
        quote.setVisible(qt.isNotEmpty())
        quote.text = qt
    }
}