package com.boni.breakingbadfacts.features.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.onError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.utils.toCharacters

class HomeViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val charactersState = intoMediator<HomeViewState.CharactersState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun doGetAllCharacters() {
        load {
            repository.getAllCharacters()
                .onSuccess { charactersState.postValue(HomeViewState.CharactersState(it.toCharacters())) }
                .onError { }
        }
    }

    sealed class HomeViewState : ViewState {
        data class CharactersState(val characterList: List<Character>) : HomeViewState()
    }
}