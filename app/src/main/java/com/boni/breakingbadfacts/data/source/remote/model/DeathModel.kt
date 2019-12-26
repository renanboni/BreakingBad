package com.boni.breakingbadfacts.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DeathModel(
    @SerializedName("death_id") val id: Int? = null,
    @SerializedName("death") val death: String? = null,
    @SerializedName("cause") val cause: String? = null,
    @SerializedName("responsible") val responsible: String? = null,
    @SerializedName("last_words") val lastWords: String? = null,
    @SerializedName("season") val season: Int? = null,
    @SerializedName("episode") val episode: Int? = null,
    @SerializedName("number_of_deaths") val numberOfDeaths: Int? = null
) : Parcelable