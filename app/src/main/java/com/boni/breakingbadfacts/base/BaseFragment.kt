package com.boni.breakingbadfacts.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment : Fragment() {

    private val viewModelInterface by lazy { this as? HasViewModel<*> }

    abstract val loadModules: List<Module>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(loadModules)
        viewModelInterface?.prepare(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(loadModules)
    }
}