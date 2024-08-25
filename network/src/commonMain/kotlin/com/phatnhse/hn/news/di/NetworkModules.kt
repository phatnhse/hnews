package com.phatnhse.hn.news.di

import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.news.datasource.HnRemoteDataSourceImpl
import com.phatnhse.hn.news.util.createHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

val networkModule = module {

    factory<HttpClient> {
        createHttpClient(baseUrl = BASE_URL)
    }

    factory<HnRemoteDataSource> {
        HnRemoteDataSourceImpl(get())
    }


}