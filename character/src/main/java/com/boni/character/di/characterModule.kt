package com.boni.character.di

import com.boni.character.ui.CharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    viewModel { CharacterViewModel(get()) }
}