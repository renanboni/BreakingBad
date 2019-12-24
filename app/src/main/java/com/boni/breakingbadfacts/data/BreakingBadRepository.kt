package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel

interface BreakingBadRepository {
    suspend fun getAllCharacters(): Result<List<CharacterModel>>
    suspend fun getQuotes(): Result<List<QuoteModel>>
    suspend fun getEpisodes(): Result<List<EpisodeModel>>
}