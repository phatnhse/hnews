package com.phatnhse.hn.threads.network.datasource

import com.phatnhse.hn.threads.network.response.SampleResponse

internal class HackerNewsRemoteDataSourceImpl : HackerNewsRemoteDataSource {

    override suspend fun getNews(): List<SampleResponse> {
        return listOf(SampleResponse("id", "title"))
    }

}