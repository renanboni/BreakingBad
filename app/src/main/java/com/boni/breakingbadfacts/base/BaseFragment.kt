package com.boni.breakingbadfacts.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private val viewModelInterface by lazy { this as? HasViewModel<*> }

    abstract val loadModules: Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
        viewModelInterface?.prepare(this)
    }

    private fun injectFeatures() = loadModules
}