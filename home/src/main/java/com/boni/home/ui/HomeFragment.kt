package com.boni.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.LoadingState
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.utils.MarginItemDecoration
import com.boni.breakingbadfacts.utils.gone
import com.boni.home.R
import com.boni.home.di.homeModule
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : HasViewModel<HomeViewModel>, BaseFragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override val viewModel: HomeViewModel
        get() = homeViewModel

    override val loadModules by lazy {
        listOf(homeModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        more.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
            findNavController().navigate(action)
        }
        episodes.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEpisodesFragment()
            findNavController().navigate(action)
        }
    }

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when (viewState) {
            is HomeViewModel.HomeViewState.CharactersState -> renderCharacters(viewState)
            is LoadingState.Show -> shimmer.startShimmerAnimation()
            is LoadingState.Hide -> shimmer.stopShimmerAnimation()
        }
    }

    private fun renderCharacters(viewState: HomeViewModel.HomeViewState.CharactersState) {
        characters.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
        characters.adapter = HomeAdapter(viewState.characterList) {
            val action =
                HomeFragmentDirections.actionHomeFragmentToCharacterFragment(
                    it
                )
            findNavController().navigate(action)
        }
        shimmer.gone()
    }
}