package com.boni.breakingbadfacts.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment
import com.boni.breakingbadfacts.base.HasViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.utils.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : HasViewModel<HomeViewModel>, BaseFragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override val viewModel: HomeViewModel
        get() = homeViewModel

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

    override fun renderState(viewState: ViewState?) {
        super.renderState(viewState)

        when (viewState) {
            is HomeViewModel.HomeViewState.CharactersState -> {
                characters.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
                characters.adapter = HomeAdapter(viewState.characterList)
            }
        }
    }
}