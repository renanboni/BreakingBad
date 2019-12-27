package com.boni.breakingbadfacts.utils

import androidx.recyclerview.widget.RecyclerView
import com.boni.breakingbadfacts.R

fun RecyclerView.addMarginBetweenItems() {
    addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
}

fun RecyclerView.addSeparatorBetweenItems() {
    addItemDecoration(
        LineItemDecoration(
            context,
            R.color.gray,
            alpha = 0.2,
            skipTopLine = true,
            skipLastLine = true
        )
    )
}