package com.boni.breakingbadfacts.data.source.remote.services

import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadService {

    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterModel>

    @GET("quotes")
    suspend fun getQuotes(): List<QuoteModel>

    @GET("episodes")
    suspend fun getEpisodes(): List<EpisodeModel>

    @GET("deaths")
    suspend fun getDeaths(): List<DeathModel>
}