package com.boni.breakingbadfacts.utils

fun String.toHumanDateFormat() {
    // MM DD AAAA
    // 09-07-2018
    split("-").also {
        val month = it[0]
        val day = it[1]
        val year = it[2]


    }
}