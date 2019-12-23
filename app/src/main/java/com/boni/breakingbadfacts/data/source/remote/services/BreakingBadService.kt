package com.boni.breakingbadfacts.data.source.remote.services

import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import retrofit2.http.GET

interface BreakingBadService {

    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterModel>
}