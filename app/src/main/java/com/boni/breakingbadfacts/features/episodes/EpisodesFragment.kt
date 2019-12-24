package com.boni.breakingbadfacts.features.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.features.characters.CharactersViewModel
import com.boni.breakingbadfacts.ui.VerticalStepper
import kotlinx.android.synthetic.main.fragment_episodes.*
import org.koin.android.viewmodel.ext.android.viewModel

class EpisodesFragment : HasViewModel<EpisodesViewModel>, BaseFragment(),
    VerticalStepper.OnVerticalStepperListener {

    private val episodesViewModel by viewModel<EpisodesViewModel>()

    override val viewModel: EpisodesViewModel
        get() = episodesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_episodes,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.getEpisodes(1)
        verticalStepper.adjustIndicators(0)
    }

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when(viewState) {
            is EpisodesViewModel.EpisodesViewState.Episodes -> {
                episodes.adapter = EpisodesAdapter(viewState.episodes)
            }
        }
    }

    private fun setupView() {
        verticalStepper.listener = this
    }

    override fun onStepperClicked(index: Int) {
        viewModel.getEpisodes(index + 1)
    }
}