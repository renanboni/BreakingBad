package com.boni.breakingbadfacts.features.model

import android.graphics.Color
import androidx.annotation.StringDef
import com.boni.breakingbadfacts.R

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
) {
    companion object {
        private const val ALIVE = "Alive"
        private const val DECEASED = "Deceased"

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