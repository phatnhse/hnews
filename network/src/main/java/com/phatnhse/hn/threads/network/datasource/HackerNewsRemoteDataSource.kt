package com.phatnhse.hn.threads.network.datasource

import com.phatnhse.hn.threads.network.response.SampleResponse

interface HackerNewsRemoteDataSource {
    suspend fun getNews(): List<SampleResponse>
}