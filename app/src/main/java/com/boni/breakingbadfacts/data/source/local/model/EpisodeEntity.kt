package com.boni.breakingbadfacts.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "episode")
data class EpisodeEntity(
    @ColumnInfo val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val season: String,
    @ColumnInfo val airDate: String,
    @ColumnInfo val characters: List<String>,
    @ColumnInfo val episode: String,
    @ColumnInfo val series: String
)