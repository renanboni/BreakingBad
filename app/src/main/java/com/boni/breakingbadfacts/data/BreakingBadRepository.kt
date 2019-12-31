package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.features.model.Episode

interface BreakingBadRepository {
    suspend fun getAllCharacters(): Result<List<Character>>
    suspend fun getQuotes(): Result<List<QuoteModel>>
    suspend fun getEpisodes(): Result<List<Episode>>
    suspend fun getDeaths(): Result<List<DeathModel>>
}