package com.boni.breakingbadfacts.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class EpisodeModel(
    @SerializedName("episode_id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("season") val season: String? = null,
    @SerializedName("air_date") val airDate: String? = null,
    @SerializedName("characters") val characters: List<String>? = null,
    @SerializedName("episode") val episode: String? = null,
    @SerializedName("series") val series: String? = null
) : Parcelable