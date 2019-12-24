package com.boni.breakingbadfacts.features.character

import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.onError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Quote
import com.boni.breakingbadfacts.utils.toQuotes

class CharacterViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val quotesState = intoMediator<CharacterState.Quotes>()

    private var currentQuote = 0
    private var quotes = listOf<Quote>()

    fun getQuotes(author: String) {
        load {
            repository.getQuotes()
                .onSuccess {
                    quotes = it.toQuotes().filter { actor -> actor.author == author }
                    quotesState.postValue(CharacterState.Quotes(quotes))
                }.onError { }
        }
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
    }
}