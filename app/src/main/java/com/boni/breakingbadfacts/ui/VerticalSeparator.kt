package com.boni.breakingbadfacts.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.utils.setBackgroundDrawableColor

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
        view.setBackgroundDrawableColor(color)
    }
}