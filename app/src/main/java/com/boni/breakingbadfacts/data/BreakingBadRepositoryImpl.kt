package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.services.BreakingBadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreakingBadRepositoryImpl(private val service: BreakingBadService) : BreakingBadRepository {

    private var allCharacters = listOf<CharacterModel>()

    override suspend fun getAllCharacters(): Result<List<CharacterModel>> {
        return if (allCharacters.isNotEmpty()) {
            Result.Success(allCharacters)
        } else {
            withContext(Dispatchers.IO) {
                try {
                    allCharacters = service.getAllCharacters()
                    Result.Success(allCharacters)
                } catch (e: Throwable) {
                    Result.Error(e)
                }
            }
        }
    }
}