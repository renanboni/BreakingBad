package com.boni.breakingbadfacts.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.boni.breakingbadfacts.R

class CircleIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    private val number: TextView by lazy { findViewById<TextView>(R.id.number) }

    init {
        LayoutInflater.from(context).inflate(R.layout.circle_indicator_layout, this)
    }

    fun setCircleIndicatorText(text: String) {
        number.text = text
    }

    fun setColor(color: Int) {
        val backgroundDrawable = DrawableCompat.wrap(number.background).mutate()
        DrawableCompat.setTint(backgroundDrawable, ContextCompat.getColor(context, color))
    }
}