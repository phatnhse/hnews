package com.phatnhse.hn.threads.di

import com.phatnhse.hn.threads.AppViewModel
import com.phatnhse.hn.threads.GetTopStoriesUseCase
import com.phatnhse.hn.threads.GetTopStoriesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModules = module {
    viewModel<AppViewModel> {
        AppViewModel(get())
    }

    factory<GetTopStoriesUseCase> {
        GetTopStoriesUseCaseImpl(
            remoteDataSource = get(),
            database = get(),
            ioDispatcher = Dispatchers.IO,
        )
    }
}

expect val platformModule: Module
