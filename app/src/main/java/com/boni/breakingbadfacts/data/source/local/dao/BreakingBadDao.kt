package com.boni.breakingbadfacts.data.source.local.dao

import androidx.room.*
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.local.model.QuoteEntity

@Dao
interface BreakingBadDao {

    @Query("SELECT * FROM episode")
    suspend fun getEpisodes(): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEpisodes(episodeList: List<EpisodeEntity>)

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characterList: List<CharacterEntity>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuotes(quoteList: List<QuoteEntity>)

    @Query("UPDATE episode SET isChecked = :isChecked WHERE id = :id")
    suspend fun setEpisodeAsViewed(id: Int, isChecked: Boolean)
}