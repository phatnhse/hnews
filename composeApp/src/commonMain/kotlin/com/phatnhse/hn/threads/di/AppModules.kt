package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.AppViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(commonModules, platformModule)
    }
}

val commonModules = module {
    viewModel<AppViewModel> {
        AppViewModel(get())
    }
}
expect val platformModule: Module
