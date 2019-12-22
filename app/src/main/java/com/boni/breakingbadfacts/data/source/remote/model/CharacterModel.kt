package com.boni.breakingbadfacts.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CharacterModel(
    @SerializedName("char_id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("occupation") val occupation: ArrayList<String>? = null,
    @SerializedName("img") val img: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("appearance") val appearance: ArrayList<Int>? = null,
    @SerializedName("nickname") val nickname: String? = null,
    @SerializedName("portrayed") val portrayed: String? = null
) : Parcelable