package com.phatnhse.hn.news.util

import kotlinx.coroutines.withTimeout

private const val RETRY_TIMES_DEFAULT = 3
private const val WINDOW_TIME = 5_000L


/**
 * Implement non-linear retry function with increment window times, 5 - 10 - 15 for each retry.
 */
suspend fun <T> retryFetch(retryTimes: Int = RETRY_TIMES_DEFAULT, block: suspend () -> T): T? {
    for (i in 0 until retryTimes) {
        try {
            val result = withTimeout(WINDOW_TIME * i) {
                block()
            }
            return result
        } catch (e: Exception) {
            continue
        }
    }

    return null
}