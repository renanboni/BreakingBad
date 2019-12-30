package com.boni.breakingbadfacts.data.source.local

import com.boni.breakingbadfacts.base.Result
import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity

interface BreakingBadLocalDataSource {
    suspend fun getEpisodes(): Result<List<EpisodeEntity>>

    suspend fun saveEpisodes(episodeList: List<EpisodeEntity>): Result<Unit>
}