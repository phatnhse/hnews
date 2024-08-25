package com.phatnhse.hn.news.util

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import org.koin.core.module.Module

private const val RETRY_TIMES_DEFAULT = 3
private const val RETRY_WINDOW_TIME = 5_000L
private const val DEFAULT_TIME_OUT = 30_000L

/**
 * Implement non-linear retry function with increment window times, 5 - 10 - 15 for each retry.
 */
suspend fun <T> retryFetch(retryTimes: Int = RETRY_TIMES_DEFAULT, block: suspend () -> T): T? {
    for (i in 0 until retryTimes) {
        try {
            val result = withTimeout(RETRY_WINDOW_TIME * i) {
                block()
            }
            return result
        } catch (e: Exception) {
            continue
        }
    }

    return null
}

private val defaultLogger = object : Logger {
    override fun log(message: String) {
        co.touchlab.kermit.Logger.i(message)
    }
}

fun createHttpClient(
    baseUrl: String,
    timeout: Long = DEFAULT_TIME_OUT,
): HttpClient {
    return HttpClient {
        install(HttpTimeout) {
            socketTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = defaultLogger
        }
        defaultRequest {
            url(baseUrl)
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }
}