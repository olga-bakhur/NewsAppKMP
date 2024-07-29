package domain.util

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    val ui: CoroutineDispatcher,
    val default: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val unconfined: CoroutineDispatcher
)
