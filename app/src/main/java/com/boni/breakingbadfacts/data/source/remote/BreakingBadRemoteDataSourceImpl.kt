package com.boni.breakingbadfacts.data.source.remote

import com.boni.breakingbadfacts.base.BaseRepository
import com.boni.breakingbadfacts.data.source.remote.services.BreakingBadService

class BreakingBadRemoteDataSourceImpl(
    private val service: BreakingBadService
) : BreakingBadRemoteDataSource, BaseRepository() {

    override suspend fun getAllCharacters() = runAsync {
        service.getAllCharacters()
    }

    override suspend fun getQuotes() = runAsync {
        service.getQuotes()
    }

    override suspend fun getEpisodes() = runAsync {
        service.getEpisodes()
    }

    override suspend fun getDeaths() = runAsync {
        service.getDeaths()
    }
}