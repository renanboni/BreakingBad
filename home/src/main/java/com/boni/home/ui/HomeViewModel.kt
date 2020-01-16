package com.boni.home.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.notifyError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Character

class HomeViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val charactersState = intoMediator<HomeViewState.CharactersState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun doGetAllCharacters() {
        load {
            repository.getAllCharacters()
                .onSuccess { charactersState.postValue(
                    HomeViewState.CharactersState(
                        it
                    )
                ) }
                .notifyError(errorLiveData)
        }
    }

    sealed class HomeViewState : ViewState {
        data class CharactersState(val characterList: List<Character>) : HomeViewState()
    }
}