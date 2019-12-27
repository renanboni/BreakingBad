package com.boni.breakingbadfacts.features.model

class Death(
    val id: Int,
    val death: String,
    val cause: String,
    val responsible: String,
    val lastWords: String,
    val season: Int,
    val episode: Int,
    val numberOfDeaths: Int,
    var img: String
)