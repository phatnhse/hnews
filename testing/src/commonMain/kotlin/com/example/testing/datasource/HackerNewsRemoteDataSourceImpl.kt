package com.example.testing.datasource

import com.example.testing.response.SampleResponse

internal class HackerNewsRemoteDataSourceImpl : HackerNewsRemoteDataSource {

    override suspend fun getNews(): List<SampleResponse> {
        return listOf(SampleResponse("id", "title"))
    }

}