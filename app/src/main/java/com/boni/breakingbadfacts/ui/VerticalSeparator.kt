package com.boni.breakingbadfacts.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
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
    init {
        LayoutInflater.from(context).inflate(R.layout.vertical_separator_layout, this)
    }
}