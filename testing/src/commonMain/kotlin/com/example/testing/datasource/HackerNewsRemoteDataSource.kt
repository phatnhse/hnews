package com.example.testing.datasource

import com.example.testing.response.SampleResponse

interface HackerNewsRemoteDataSource {
    suspend fun getNews(): List<SampleResponse>
}