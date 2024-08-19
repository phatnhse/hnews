package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.AppViewModel
import io.ktor.client.HttpClient
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val client: HttpClient

val commonModules = module {
    viewModel<AppViewModel> {
        AppViewModel(get())
    }
}

expect val platformModule: Module
