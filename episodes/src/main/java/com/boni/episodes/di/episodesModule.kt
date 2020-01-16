package com.boni.episodes.di

import com.boni.episodes.ui.EpisodesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {
    viewModel { EpisodesViewModel(get()) }
}