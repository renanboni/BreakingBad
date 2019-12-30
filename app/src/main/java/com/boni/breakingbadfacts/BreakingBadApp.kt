package com.boni.breakingbadfacts

import androidx.multidex.MultiDexApplication
import com.boni.breakingbadfacts.di.networkModule
import com.boni.breakingbadfacts.di.repositoryModule
import com.boni.breakingbadfacts.di.viewModelModule
import org.koin.android.ext.koin.androidContext

class BreakingBadApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@BreakingBadApp)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
    }
}