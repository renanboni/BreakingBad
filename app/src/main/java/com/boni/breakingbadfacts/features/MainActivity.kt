package com.boni.breakingbadfacts.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boni.breakingbadfacts.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
    }
}