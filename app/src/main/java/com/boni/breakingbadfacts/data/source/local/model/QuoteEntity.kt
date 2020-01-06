package com.boni.breakingbadfacts.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
class QuoteEntity(
    @ColumnInfo @PrimaryKey val id: Long,
    @ColumnInfo val quote: String,
    @ColumnInfo val author: String,
    @ColumnInfo val series: String
)