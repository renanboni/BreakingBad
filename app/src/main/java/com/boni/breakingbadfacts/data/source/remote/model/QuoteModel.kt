package com.boni.breakingbadfacts.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class QuoteModel(
    @SerializedName("quote_id") val id: Int? = null,
    @SerializedName("quote") val quote: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("series") val series: String? = null
) : Parcelable