package com.boni.breakingbadfacts.utils

import com.boni.breakingbadfacts.R

fun String.toStatusColor(): Int {
    return if (this == "Alive") {
        R.color.green
    } else {
        android.R.color.holo_red_dark
    }
}