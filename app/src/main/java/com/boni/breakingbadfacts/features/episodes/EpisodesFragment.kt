package com.boni.breakingbadfacts.features.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.LoadingState
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.ui.VerticalStepper
import com.boni.breakingbadfacts.utils.LineItemDecoration
import com.boni.breakingbadfacts.utils.addSeparatorBetweenItems
import com.boni.breakingbadfacts.utils.gone
import kotlinx.android.synthetic.main.fragment_episodes.*
import kotlinx.android.synthetic.main.fragment_episodes.episodes
import kotlinx.android.synthetic.main.fragment_episodes.shimmer
import org.koin.android.viewmodel.ext.android.viewModel

class EpisodesFragment :
    HasViewModel<EpisodesViewModel>,
    BaseFragment(),
    VerticalStepper.OnVerticalStepperListener {

    private val episodesViewModel by viewModel<EpisodesViewModel>()

    override val viewModel: EpisodesViewModel
        get() = episodesViewModel

    private val args: EpisodesFragmentArgs by navArgs()

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
        viewModel.getEpisodes(args.season)
        verticalStepper.adjustIndicators(args.season)
    }

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when (viewState) {
            is EpisodesViewModel.EpisodesViewState.Episodes -> renderEpisodes(viewState)
            is LoadingState.Show -> shimmer.startShimmerAnimation()
            is LoadingState.Hide -> shimmer.stopShimmerAnimation()
        }
    }

    private fun renderEpisodes(viewState: EpisodesViewModel.EpisodesViewState.Episodes) {
        episodes.adapter = EpisodesAdapter(viewState.episodes)
        episodes.addSeparatorBetweenItems()
        shimmer.gone()
    }

    private fun setupView() {
        verticalStepper.listener = this
    }

    override fun onStepperClicked(index: Int) {
        viewModel.getEpisodes(index + 1)
    }
}