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
