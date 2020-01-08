package com.boni.breakingbadfacts.data.source.remote.prefs

import android.content.SharedPreferences

class EpisodePreferences(private val sharedPreferences: SharedPreferences) {

    fun alreadyViewed(id: Int): Boolean {
        return sharedPreferences.getBoolean(id.toString(), false)
    }

    fun setAsViewed(id: Int, viewed: Boolean) {
        sharedPreferences.edit().putBoolean(id.toString(), viewed).apply()
    }
}