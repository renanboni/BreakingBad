package com.boni.breakingbadfacts.data.source.local

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.local.dao.BreakingBadDao
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreakingBadLocalDataSourceImpl(
    private val breakingBadDao: BreakingBadDao
) : BreakingBadLocalDataSource {
    override suspend fun getEpisodes() = withContext(Dispatchers.IO) {
        try {
            Result.Success(breakingBadDao.getEpisodes())
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun saveEpisodes(episodeList: List<EpisodeEntity>) =
        withContext(Dispatchers.IO) {
            try {
                breakingBadDao.saveEpisodes(episodeList)
                Result.Success(Unit)
            } catch (e: Throwable) {
                Result.Error(e)
            }
        }

    override suspend fun getCharacters(): Result<List<CharacterEntity>> =
        withContext(Dispatchers.IO) {
            try {
                Result.Success(breakingBadDao.getCharacters())
            } catch (e: Throwable) {
                Result.Error(e)
            }
        }

    override suspend fun saveCharacters(characterList: List<CharacterEntity>) =
        withContext(Dispatchers.IO) {
            try {
                breakingBadDao.saveCharacters(characterList)
                Result.Success(Unit)
            } catch (e: Throwable) {
                Result.Error(e)
            }
        }
}

