package com.boni.breakingbadfacts.data.source.local

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.local.dao.BreakingBadDao
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
}

