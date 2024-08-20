package com.example.testing.di

import com.example.testing.datasource.HackerNewsRemoteDataSource
import com.example.testing.datasource.HackerNewsRemoteDataSourceImpl
import org.koin.dsl.module

val networkModules = module {
    factory<HackerNewsRemoteDataSource>() {
        HackerNewsRemoteDataSourceImpl()
    }
}