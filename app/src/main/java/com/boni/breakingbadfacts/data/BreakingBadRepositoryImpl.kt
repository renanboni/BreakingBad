package com.boni.breakingbadfacts.data

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.base.get
import com.boni.breakingbadfacts.base.isSuccess
import com.boni.breakingbadfacts.data.source.local.BreakingBadLocalDataSource
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.remote.BreakingBadRemoteDataSource
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.data.source.remote.services.BreakingBadService
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.features.model.Episode
import com.boni.breakingbadfacts.utils.toCharacters
import com.boni.breakingbadfacts.utils.toCharactersEntity
import com.boni.breakingbadfacts.utils.toEpisodes
import com.boni.breakingbadfacts.utils.toEpisodesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreakingBadRepositoryImpl(
    private val service: BreakingBadService,
    private val local: BreakingBadLocalDataSource,
    private val remote: BreakingBadRemoteDataSource
) : BreakingBadRepository {

    private var characters = listOf<Character>()
    private var quotes = listOf<QuoteModel>()
    private var episodes = listOf<Episode>()
    private var deaths = listOf<DeathModel>()

    override suspend fun getAllCharacters(): Result<List<Character>> {
        if (characters.isNotEmpty()) {
            return Result.Success(characters)
        }

        val localCharacters = local.getCharacters()

        if (localCharacters.isSuccess() && localCharacters.get().isNotEmpty()) {
            val characters = localCharacters.get().toCharacters()
            refreshCacheCharacters(characters)
            return Result.Success(characters)
        }

        val remoteCharacters = remote.getAllCharacters()

        if (remoteCharacters.isSuccess()) {
            val characters = remoteCharacters.get()
            refreshCacheCharacters(characters.toCharacters())
            refreshLocalCharacters(characters.toCharactersEntity())
        }

        return Result.Error(Throwable("Failed to fetch data from all available sources"))
    }

    private fun refreshCacheCharacters(characterList: List<Character>) {
        characters = characterList
    }

    private suspend fun refreshLocalCharacters(characterList: List<CharacterEntity>) {
        local.saveCharacters(characterList)
    }

    override suspend fun getEpisodes(): Result<List<Episode>> {
        // try to get it locally
        if (episodes.isNotEmpty()) {
            return Result.Success(episodes)
        }

        // try to get data locally
        val localEpisodes = local.getEpisodes()

        if (localEpisodes.isSuccess() && localEpisodes.get().isNotEmpty()) {
            val episodes = localEpisodes.get().toEpisodes()
            refreshCacheEpisodes(episodes)
            return Result.Success(episodes)
        }

        // local data failed, try to get remotely
        val remoteEpisodes = remote.getEpisodes()

        if (remoteEpisodes.isSuccess()) {
            val episodes = remoteEpisodes.get()
            refreshCacheEpisodes(episodes.toEpisodes())
            refreshLocalEpisodes(episodes.toEpisodesEntity())
            return Result.Success(this.episodes)
        }

        return Result.Error(Throwable("Failed to fetch data from all available sources"))
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

    private fun refreshCacheEpisodes(episodeList: List<Episode>) {
        this.episodes = episodeList
    }

    private suspend fun refreshLocalEpisodes(episodeList: List<EpisodeEntity>) {
        local.saveEpisodes(episodeList)
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

    override suspend fun setEpisodeAsViewed(id: Int, viewed: Boolean): Result<Unit> {
        return local.setEpisodeAsViewed(id, viewed)
    }
}