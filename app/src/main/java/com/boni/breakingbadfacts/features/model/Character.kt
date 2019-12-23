package com.boni.breakingbadfacts.features.model

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.StringDef
import kotlinx.android.parcel.Parcelize

@Parcelize
class Character(
    val id: Long,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    @StatusType val status: String,
    val appearance: List<Int>,
    val nickname: String,
    val portrayed: String
) : Parcelable {
    companion object {
        const val ALIVE = "Alive"
        const val DECEASED = "Deceased"

        @StringDef(ALIVE, DECEASED)
        annotation class StatusType

        fun getStatusColor(@StatusType status: String): Int {
            return when (status) {
                ALIVE -> Color.GREEN
                DECEASED -> Color.RED
                else -> Color.LTGRAY
            }
        }
    }
}