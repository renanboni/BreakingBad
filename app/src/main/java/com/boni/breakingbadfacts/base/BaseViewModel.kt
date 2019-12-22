package com.boni.breakingbadfacts.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel(), LifecycleObserver {

    val viewState = MediatorLiveData<ViewState>()
    private val viewStateList: MutableList<MutableLiveData<*>> = mutableListOf()

    protected val loadingLiveData = intoMediator<LoadingState>()
    protected val errorLiveData = intoMediator<ErrorState>()

    protected fun <T : ViewState> intoMediator(shouldAddInViewStateList: Boolean = true): MutableLiveData<T> {
        val liveData = MutableLiveData<T>()
        viewState.addSource(liveData) {
            viewState.value = it
        }

        if (shouldAddInViewStateList) {
            viewStateList.add(liveData)
        }

        return liveData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        viewStateList.forEach {
            if (it.value != null) {
                viewState.postValue(it.value as? ViewState)
            }
        }
    }

    protected fun launch(asyncBlock: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            asyncBlock()
        }
    }

    protected fun load(asyncBlock: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            showLoading()
            asyncBlock()
            hideLoading()
        }
    }

    private fun showLoading() {
        loadingLiveData.postValue(LoadingState.Show)
    }

    private fun hideLoading() {
        loadingLiveData.postValue(LoadingState.Hide)
    }

    override fun onCleared() {
        viewStateList.clear()
    }
}