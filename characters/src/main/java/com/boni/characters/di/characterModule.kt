package com.boni.characters.di

import com.boni.characters.ui.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
}