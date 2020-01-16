package com.boni.breakingbadfacts

import android.app.Application
import com.boni.breakingbadfacts.di.networkModule
import com.boni.breakingbadfacts.di.repositoryModule
import org.koin.android.ext.koin.androidContext

class BreakingBadApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@BreakingBadApp)
            modules(listOf(networkModule, repositoryModule))
        }
    }
}