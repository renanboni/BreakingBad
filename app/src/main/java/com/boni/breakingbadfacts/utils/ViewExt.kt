package com.boni.breakingbadfacts.utils

import android.view.View

fun View.setVisible(shouldShow: Boolean) {
    visibility = if (shouldShow) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
