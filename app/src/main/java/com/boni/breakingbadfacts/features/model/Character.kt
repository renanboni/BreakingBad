package com.boni.breakingbadfacts.features.model

class Character(
    val id: Long,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val appearance: List<Int>,
    val nickname: String,
    val portrayed: String
)