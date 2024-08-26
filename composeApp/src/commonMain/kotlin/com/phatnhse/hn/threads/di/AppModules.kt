package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.AppViewModel
import com.phatnhse.hn.threads.database.AppDatabase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModules = module {
    viewModel<AppViewModel> {
        AppViewModel(get(), get())
    }
}

expect val platformModule: Module

expect fun getDatabase(): AppDatabase
