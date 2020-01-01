package com.boni.breakingbadfacts.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository : KoinComponent {
    private val dispatcher: CoroutineDispatcher by inject()

    suspend fun <T : Any> runAsync(asyncBlock: suspend CoroutineScope.() -> T) =
        withContext(dispatcher) {
            try {
                Result.Success(asyncBlock())
            } catch (e: Throwable) {
                Result.Error(e)
            }
        }
}