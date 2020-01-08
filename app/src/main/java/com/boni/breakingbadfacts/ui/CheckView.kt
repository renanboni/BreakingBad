package com.boni.breakingbadfacts.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.boni.breakingbadfacts.R
import com.boni.breakingbadfacts.utils.setBackgroundDrawableColor
import com.boni.breakingbadfacts.utils.setVisible

class CheckView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    private var isChecked = false

    var listener: OnCheckListener? = null

    private val checkedView: ImageView by lazy { findViewById<ImageView>(R.id.checked) }
    private val background: FrameLayout by lazy { findViewById<FrameLayout>(R.id.root) }

    init {
        LayoutInflater.from(context).inflate(R.layout.check_view, this)

       /* setOnClickListener {
            isChecked = !isChecked
            listener?.onChecked(isChecked)

            adjustColors()
        }*/
    }

    fun setChecked(isChecked: Boolean) {
        checkedView.setVisible(isChecked)

        if (isChecked) {
            background.setBackgroundDrawableColor(R.color.red)
        } else {
            background.setBackgroundDrawableColor(R.color.disabled_black)
        }
    }

    interface OnCheckListener {
        fun onChecked(isChecked: Boolean)
    }
}