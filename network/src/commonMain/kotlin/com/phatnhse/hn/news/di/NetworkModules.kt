package com.phatnhse.hn.news.di

import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.news.datasource.HnRemoteDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    @OptIn(ExperimentalSerializationApi::class)
    factory<HttpClient> {
        HttpClient {
            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.i(message)
                    }
                }
            }
            defaultRequest {
                header("Content-Type", "application/json")
                url("https://hacker-news.firebaseio.com/v0/")
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }

        }
    }

    factory<HnRemoteDataSource> {
        HnRemoteDataSourceImpl(get())
    }


}