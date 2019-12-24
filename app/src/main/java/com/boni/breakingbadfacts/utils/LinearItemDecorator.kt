package com.boni.breakingbadfacts.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class LineItemDecoration @JvmOverloads constructor(
    context: Context,
    @ColorRes color: Int,
    private val alpha: Double = 1.0,
    private val margin: Int = 0,
    private val paddingFirst: Int = 0,
    private val paddingLast: Int = 0,
    private val lineSize: Int = 1.asDp(),
    private val skipTopLine: Boolean = true,
    private val skipLastLine: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val divider = ColorDrawable(ContextCompat.getColor(context, color)).apply {
        alpha = (this@LineItemDecoration.alpha * 255.9).roundToInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        var lastPosition = 1
        parent.adapter?.itemCount?.let { lastPosition = it - 1 }

        outRect.top = if (position == 0) {
            paddingFirst
        } else {
            0
        }

        outRect.bottom = if (position == lastPosition) {
            paddingLast
        } else {
            0
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)

        val firstPosition = 0
        val lastPosition = parent.childCount - 1
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin

        for (position in firstPosition until parent.childCount) {

            val child = parent.getChildAt(position)
            val params = child.layoutParams as RecyclerView.LayoutParams

            if (position == firstPosition && skipTopLine.not()) {
                val top = child.top + params.topMargin
                val bottom = top + lineSize
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            } else if (position == lastPosition && skipLastLine) {
                continue
            }

            val top = child.bottom + params.bottomMargin
            val bottom = top + lineSize

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
    }
}