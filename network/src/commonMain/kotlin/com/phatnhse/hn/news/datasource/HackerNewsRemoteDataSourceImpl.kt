package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.Story

internal class HackerNewsRemoteDataSourceImpl : HackerNewsRemoteDataSource {
    override suspend fun getTopStories(): List<Story> {
        TODO("Not yet implemented")
    }
}