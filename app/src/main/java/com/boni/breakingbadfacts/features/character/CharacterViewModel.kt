package com.boni.breakingbadfacts.features.character

import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.onError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.features.model.Death
import com.boni.breakingbadfacts.features.model.Quote
import com.boni.breakingbadfacts.utils.toDeaths
import com.boni.breakingbadfacts.utils.toQuotes

class CharacterViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val quotesState = intoMediator<CharacterState.Quotes>()
    private val deathsState = intoMediator<CharacterState.Deaths>()

    private var currentQuote = 0
    private var quotes = listOf<Quote>()

    fun getQuotes(author: String) {
        load {
            repository.getQuotes()
                .onSuccess { onGetQuoteSuccess(it, author) }
                .onError { }
        }
    }

    fun getDeaths(author: String) {
        launch {
            repository.getDeaths()
                .onSuccess { onGetDeathsSuccess(it, author) }
                .onError { }
        }
    }

    private fun onGetDeathsSuccess(
        it: List<DeathModel>,
        author: String
    ) {
        val deaths = it.toDeaths().filter { it.responsible == author }
        deathsState.postValue(CharacterState.Deaths(deaths))
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