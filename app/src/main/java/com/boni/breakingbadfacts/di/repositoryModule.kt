package com.boni.breakingbadfacts.di

import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.data.BreakingBadRepositoryImpl
import com.boni.breakingbadfacts.data.source.local.BreakingBadLocalDataSource
import com.boni.breakingbadfacts.data.source.local.BreakingBadLocalDataSourceImpl
import com.boni.breakingbadfacts.data.source.local.db.BreakingBadDatabase
import com.boni.breakingbadfacts.data.source.remote.BreakingBadRemoteDataSource
import com.boni.breakingbadfacts.data.source.remote.BreakingBadRemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<BreakingBadRepository> { BreakingBadRepositoryImpl(get(), get(), get()) }
    single { BreakingBadDatabase.buildDatabase(androidContext()).breakingBadDao() }
    single<BreakingBadLocalDataSource> { BreakingBadLocalDataSourceImpl(get()) }
    single<BreakingBadRemoteDataSource> { BreakingBadRemoteDataSourceImpl(get()) }
    single { Dispatchers.IO }
}
