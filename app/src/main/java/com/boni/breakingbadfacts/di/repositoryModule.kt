package com.boni.breakingbadfacts.di

import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.data.BreakingBadRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<BreakingBadRepository> { BreakingBadRepositoryImpl(get()) }
}
