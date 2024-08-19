package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.getDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> { getDatabase() }
    }
//@OptIn(ExperimentalSerializationApi::class)
actual val client: HttpClient
    get() = HttpClient(OkHttp) {
        //Timeout plugin to set up timeout milliseconds for client
        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }
        //Logging plugin combined with kermit(KMP Logger library)
//        install(Logging) {
//            logger = Logger.DEFAULT
//            level = LogLevel.ALL
//            logger = object: Logger {
//                override fun log(message: String) {
//                    co.touchlab.kermit.Logger.d(tag = "KtorClient") {
//                        message
//                    }
//                }
//            }
//        }
        //We can configure the BASE_URL and also
        //the deafult headers by defaultRequest builder
        defaultRequest {
            header("Content-Type", "application/json")
//            header("Authorization", "Bearer ${BuildKonfig.OPENAI_API_KEY}")
            url("https://api.openai.com/v1/")
        }
        //ContentNegotiation plugin for negotiationing media types between the client and server
        /*install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }*/
    }