package com.boni.breakingbadfacts.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.view.children
import com.boni.breakingbadfacts.R

class VerticalStepper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyleAttr
) {
    private var circleIndicators = mutableListOf<CircleIndicator>()
    private var verticalSeparators = mutableListOf<VerticalSeparator>()

    private var quantity = 0

    private var enabledColor = 0
    private var disabledColor = 0

    var listener: OnVerticalStepperListener? = null

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        bindAttrs(context, attrs, defStyleAttr)
    }

    private fun bindAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.VerticalStepper, defStyleAttr, 0)

        quantity = typedArray.getInt(R.styleable.VerticalStepper_quantity, 0)
        enabledColor = typedArray.getResourceId(R.styleable.VerticalStepper_enabledColor, 0)
        disabledColor = typedArray.getResourceId(R.styleable.VerticalStepper_disabledColor, 0)

        typedArray.recycle()
        setupIndicators()
        setOnTouchListener { _, event ->
            val x = event.x
            val y = event.y

            children.forEach { child ->
                if (child is CircleIndicator) {
                    if (x >= child.left && x <= child.right && y >= child.top && y <= child.bottom) {
                        adjustIndicators(circleIndicators.indexOf(child))
                        return@setOnTouchListener false
                    }
                }
            }

            true
        }
    }

    fun adjustIndicators(index: Int) {
        for (i in 0..index) {
            circleIndicators[i].setColor(R.color.red)

            if (i - 1 >= 0) {
                verticalSeparators[i - 1].setColor(R.color.red)
            }
        }

        for (i in index + 1 until circleIndicators.count()) {
            circleIndicators[i].setColor(R.color.disabled_black)

            if (i <= verticalSeparators.count()) {
                if (i - 1 >= 0) {
                    verticalSeparators[i - 1].setColor(R.color.disabled_black)
                }
            }
        }

        listener?.onStepperClicked(index)
    }

    private fun setupIndicators() {
        for (i in 0 until quantity) {
            if (i == quantity - 1) {
                val indicator = CircleIndicator(context).also {
                    it.setCircleIndicatorText((i + 1).toString())
                }
                circleIndicators.add(indicator)

                addView(indicator)
            } else {
                val indicator = CircleIndicator(context).also {
                    it.setCircleIndicatorText((i + 1).toString())
                }
                val separator = VerticalSeparator(context)

                circleIndicators.add(indicator)
                verticalSeparators.add(separator)

                addView(indicator)
                addView(separator)
            }
        }
    }

    interface OnVerticalStepperListener {
        fun onStepperClicked(index: Int)
    }
}