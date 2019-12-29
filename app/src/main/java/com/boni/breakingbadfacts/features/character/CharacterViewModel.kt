package com.boni.breakingbadfacts.features.character

import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ErrorState
import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.getOrThrow
import com.boni.breakingbadfacts.base.notifyError
import com.boni.breakingbadfacts.base.onError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.features.model.Death
import com.boni.breakingbadfacts.features.model.Quote
import com.boni.breakingbadfacts.utils.toCharacters
import com.boni.breakingbadfacts.utils.toDeaths
import com.boni.breakingbadfacts.utils.toQuotes
import kotlinx.coroutines.async

class CharacterViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val quotesState = intoMediator<CharacterState.Quotes>()
    private val deathsState = intoMediator<CharacterState.Deaths>()

    private var currentQuote = 0
    private var quotes = listOf<Quote>()

    fun getQuotes(author: String) {
        load {
            repository.getQuotes()
                .onSuccess { onGetQuoteSuccess(it, author) }
                .notifyError(errorLiveData)
        }
    }

    fun getDeaths(author: String) {
        launch {
            val deathListJob = async { repository.getDeaths()  }
            val characterListJob = async { repository.getAllCharacters() }

            onGetCharactersSuccess(deathListJob.await(), characterListJob.await(), author)
        }
    }

    private fun onGetCharactersSuccess(
        deathListJob: Result<List<DeathModel>>,
        characterListJob: Result<List<CharacterModel>>,
        author: String
    ) {
        try {
            val deathList = deathListJob.getOrThrow().toDeaths().filter { it.responsible == author }
            val characterList = characterListJob.getOrThrow().toCharacters()

            deathList.forEach { death ->
                characterList.find { character -> death.death == character.name }?.let {
                    death.img = it.img
                }
            }

            deathsState.postValue(CharacterState.Deaths(deathList))
        } catch (e: Throwable) {
            errorLiveData.postValue(ErrorState(e.localizedMessage))
        }
    }

    private fun onGetQuoteSuccess(
        it: List<QuoteModel>,
        author: String
    ) {
        quotes = it.toQuotes().filter { actor -> actor.author == author }
        quotesState.postValue(CharacterState.Quotes(quotes))
        getDeaths(author)
    }

    fun getNextQuote(): String {
        if (quotes.isEmpty()) {
            return ""
        }
        if (currentQuote > quotes.count() - 1) {
            currentQuote = 0
        }
        val quote = quotes[currentQuote].quote
        currentQuote++
        return quote
    }

    sealed class CharacterState : ViewState {
        data class Quotes(val quotes: List<Quote>) : CharacterState()
        data class Deaths(val deaths: List<Death>) : CharacterState()
    }
}