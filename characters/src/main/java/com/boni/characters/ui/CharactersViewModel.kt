package com.boni.characters.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.notifyError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Character

class CharactersViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val charactersState = intoMediator<CharactersViewState.CharactersState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun doGetAllCharacters() {
        load {
            repository.getAllCharacters()
                .onSuccess { charactersState.postValue(
                    CharactersViewState.CharactersState(
                        it
                    )
                ) }
                .notifyError(errorLiveData)
        }
    }

    sealed class CharactersViewState : ViewState {
        data class CharactersState(val characterList: List<Character>) : CharactersViewState()
    }
}