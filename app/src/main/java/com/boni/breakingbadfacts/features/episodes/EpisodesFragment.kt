package com.boni.breakingbadfacts.features.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.base.BaseFragment

class EpisodesFragment: BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_episodes,
            container,
            false
        )
    }
}