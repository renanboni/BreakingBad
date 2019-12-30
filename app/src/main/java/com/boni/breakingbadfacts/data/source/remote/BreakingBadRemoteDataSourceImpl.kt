package com.boni.breakingbadfacts.data.source.remote

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.remote.services.BreakingBadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreakingBadRemoteDataSourceImpl(
    private val service: BreakingBadService
) : BreakingBadRemoteDataSource {

    override suspend fun getAllCharacters() = withContext(Dispatchers.IO) {
        try {
            Result.Success(service.getAllCharacters())
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getQuotes() = withContext(Dispatchers.IO) {
        try {
            Result.Success(service.getQuotes())
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getEpisodes() = withContext(Dispatchers.IO) {
        try {
            Result.Success(service.getEpisodes())
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getDeaths() = withContext(Dispatchers.IO) {
        try {
            Result.Success(service.getDeaths())
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}