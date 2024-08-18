package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { getDatabase() }
    }