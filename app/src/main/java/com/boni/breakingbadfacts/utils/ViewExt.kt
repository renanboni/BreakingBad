package com.boni.breakingbadfacts.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

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

fun View.setBackgroundDrawableColor(color: Int) {
    val backgroundDrawable = DrawableCompat.wrap(background).mutate()
    DrawableCompat.setTint(backgroundDrawable, ContextCompat.getColor(context, color))
}
