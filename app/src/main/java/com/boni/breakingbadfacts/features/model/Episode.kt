package com.boni.breakingbadfacts.features.model

class Episode(
    val id: Int,
    val title: String,
    val season: String,
    val airDate: String,
    val characters: List<String>,
    val episode: String,
    val series: String,
    var isChecked: Boolean
)