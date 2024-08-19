package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.getDatabase
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { getDatabase() }
    }
actual val client: HttpClient
    get() = TODO("Not yet implemented")