package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.AppViewModel
import io.ktor.client.HttpClient
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModules = module {
    viewModel<AppViewModel> {
        AppViewModel(get(), get())
    }

    single {
        HttpClient {
            engine {

            }
        }
    }
}

expect val platformModule: Module
