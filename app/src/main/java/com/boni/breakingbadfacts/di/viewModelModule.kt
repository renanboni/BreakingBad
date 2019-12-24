package com.boni.breakingbadfacts.di

import com.boni.breakingbadfacts.features.character.CharacterViewModel
import com.boni.breakingbadfacts.features.characters.CharactersViewModel
import com.boni.breakingbadfacts.features.episodes.EpisodesViewModel
import com.boni.breakingbadfacts.features.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
}