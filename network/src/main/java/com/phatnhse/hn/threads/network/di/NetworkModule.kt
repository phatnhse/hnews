package com.phatnhse.hn.threads.network.di

import com.phatnhse.hn.threads.network.datasource.HackerNewsRemoteDataSource
import com.phatnhse.hn.threads.network.datasource.HackerNewsRemoteDataSourceImpl
import org.koin.dsl.module

val networkModules = module {
    factory<HackerNewsRemoteDataSource>() {
        HackerNewsRemoteDataSourceImpl()
    }
}