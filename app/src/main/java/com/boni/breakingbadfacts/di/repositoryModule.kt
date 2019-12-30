package com.boni.breakingbadfacts.di

import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.data.BreakingBadRepositoryImpl
import com.boni.breakingbadfacts.data.source.local.db.BreakingBadDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<BreakingBadRepository> { BreakingBadRepositoryImpl(get()) }
    single { BreakingBadDatabase.buildDatabase(androidContext()).breakingBadDao() }
}
