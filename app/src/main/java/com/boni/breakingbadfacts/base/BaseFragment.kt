package com.boni.breakingbadfacts.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private val viewModelInterface by lazy { this as? HasViewModel<*> }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelInterface?.prepare(this)
    }
}