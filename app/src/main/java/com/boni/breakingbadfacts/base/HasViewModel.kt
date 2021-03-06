package com.boni.breakingbadfacts.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.boni.breakingbadfacts.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

interface HasViewModel<T : BaseViewModel> {

    val viewModel: T

    private val loadingInterface: LoadingInterface?
        get() = this as? LoadingInterface

    private fun getActivity(): FragmentActivity {
        return if (this is Fragment) {
            checkNotNull(this.activity)
        } else {
            this as AppCompatActivity
        }
    }

    fun prepare(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(viewModel)
        viewModel.viewState.observe(lifecycleOwner, Observer {
            renderState(it)
        })
    }

    fun renderState(viewState: ViewState?) {
        when (viewState) {
            is LoadingState -> renderLoadingState(viewState)
            is ErrorState -> renderErrorState(viewState)
        }
    }

    private fun renderErrorState(state: ErrorState) {
        MaterialAlertDialogBuilder(getActivity())
            .setTitle(getActivity().getString(R.string.error))
            .setMessage(state.message)
            .setPositiveButton(getActivity().getString(R.string.ok), null)
            .show()
    }

    private fun renderLoadingState(state: LoadingState) {
        when (state) {
            is LoadingState.Show -> loadingInterface?.showLoading()
            is LoadingState.Hide -> loadingInterface?.hideLoading()
        }
    }
}