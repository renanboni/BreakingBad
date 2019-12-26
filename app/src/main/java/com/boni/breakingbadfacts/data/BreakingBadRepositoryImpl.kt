package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.data.source.remote.services.BreakingBadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreakingBadRepositoryImpl(private val service: BreakingBadService) : BreakingBadRepository {

    private var allCharacters = listOf<CharacterModel>()
    private var quotes = listOf<QuoteModel>()
    private var episodes = listOf<EpisodeModel>()
    private var deaths = listOf<DeathModel>()

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

    override suspend fun getQuotes(): Result<List<QuoteModel>> {
        return if (quotes.isNotEmpty()) {
            Result.Success(quotes)
        } else {
            withContext(Dispatchers.IO) {
                try {
                    quotes = service.getQuotes()
                    Result.Success(quotes)
                } catch (e: Throwable) {
                    Result.Error(e)
                }
            }
        }
    }

    override suspend fun getEpisodes(): Result<List<EpisodeModel>> {
        return if (episodes.isNotEmpty()) {
            Result.Success(episodes)
        } else {
            withContext(Dispatchers.IO) {
                try {
                    episodes = service.getEpisodes()
                    Result.Success(episodes)
                } catch (e: Throwable) {
                    Result.Error(e)
                }
            }
        }
    }

    override suspend fun getDeaths(): Result<List<DeathModel>> {
        return if (deaths.isNotEmpty()) {
            Result.Success(deaths)
        } else {
            withContext(Dispatchers.IO) {
                try {
                    deaths = service.getDeaths()
                    Result.Success(deaths)
                } catch (e: Throwable) {
                    Result.Error(e)
                }
            }
        }
    }
}