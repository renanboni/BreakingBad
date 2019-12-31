package com.boni.breakingbadfacts.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
class CharacterEntity(
    @ColumnInfo @PrimaryKey val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val birthday: String,
    @ColumnInfo val occupation: List<String>,
    @ColumnInfo val img: String,
    @ColumnInfo val status: String,
    @ColumnInfo val appearance: List<Int>,
    @ColumnInfo val nickname: String,
    @ColumnInfo val portrayed: String
)