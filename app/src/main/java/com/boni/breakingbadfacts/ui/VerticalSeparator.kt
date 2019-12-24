package com.boni.breakingbadfacts.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.boni.breakingbadfacts.R

class VerticalSeparator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {
    private val view: View by lazy { findViewById<View>(R.id.view) }

    init {
        LayoutInflater.from(context).inflate(R.layout.vertical_separator_layout, this)
    }

    fun setColor(color: Int) {
        val backgroundDrawable = DrawableCompat.wrap(view.background).mutate()
        DrawableCompat.setTint(backgroundDrawable, ContextCompat.getColor(context, color))
    }
}