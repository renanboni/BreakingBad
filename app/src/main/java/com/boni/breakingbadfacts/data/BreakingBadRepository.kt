package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel

interface BreakingBadRepository {
    suspend fun getAllCharacters(): Result<List<CharacterModel>>
}