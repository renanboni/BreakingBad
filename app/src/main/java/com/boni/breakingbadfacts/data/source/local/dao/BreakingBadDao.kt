package com.boni.breakingbadfacts.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity

@Dao
interface BreakingBadDao {

    @Query("SELECT * FROM episode")
    suspend fun getEpisodes(): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEpisodes(episodeList: List<EpisodeEntity>)
}