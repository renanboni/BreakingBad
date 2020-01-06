package com.boni.breakingbadfacts.data.source.local

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.local.model.QuoteEntity

interface BreakingBadLocalDataSource {
    suspend fun getEpisodes(): Result<List<EpisodeEntity>>

    suspend fun saveEpisodes(episodeList: List<EpisodeEntity>): Result<Unit>

    suspend fun getCharacters(): Result<List<CharacterEntity>>

    suspend fun saveCharacters(characterList: List<CharacterEntity>): Result<Unit>

    suspend fun getQuotes(): Result<List<QuoteEntity>>

    suspend fun saveQuotes(quoteList: List<QuoteEntity>): Result<Unit>
}