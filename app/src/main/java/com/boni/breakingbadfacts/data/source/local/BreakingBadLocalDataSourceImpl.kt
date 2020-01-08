package com.boni.breakingbadfacts.data.source.local

import com.boni.breakingbadfacts.base.BaseRepository
import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.local.dao.BreakingBadDao
import com.boni.breakingbadfacts.data.source.local.model.CharacterEntity
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.local.model.QuoteEntity

class BreakingBadLocalDataSourceImpl(
    private val breakingBadDao: BreakingBadDao
) : BreakingBadLocalDataSource, BaseRepository() {

    override suspend fun setEpisodeAsViewed(id: Int, isViewed: Boolean) = runAsync {
        breakingBadDao.setEpisodeAsViewed(id, isViewed)
    }

    override suspend fun getEpisodes() = runAsync {
        breakingBadDao.getEpisodes()
    }

    override suspend fun saveEpisodes(episodeList: List<EpisodeEntity>) = runAsync {
        breakingBadDao.saveEpisodes(episodeList)
    }

    override suspend fun getCharacters() = runAsync {
        breakingBadDao.getCharacters()
    }

    override suspend fun saveCharacters(characterList: List<CharacterEntity>) = runAsync {
        breakingBadDao.saveCharacters(characterList)
    }

    override suspend fun getQuotes() = runAsync {
        breakingBadDao.getQuotes()
    }

    override suspend fun saveQuotes(quoteList: List<QuoteEntity>) = runAsync {
        breakingBadDao.saveQuotes(quoteList)
    }
}

