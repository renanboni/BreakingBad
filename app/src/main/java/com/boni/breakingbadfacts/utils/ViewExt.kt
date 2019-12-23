package com.boni.breakingbadfacts.utils

import android.view.View

fun View.setVisible(shouldShow: Boolean) {
    visibility = if (shouldShow) {
        View.VISIBLE
    } else {
        View.GONE
    }
}