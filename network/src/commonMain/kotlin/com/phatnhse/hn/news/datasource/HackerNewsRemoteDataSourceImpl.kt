package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.Story
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class HackerNewsRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : HackerNewsRemoteDataSource {
    override suspend fun getTopStories(): List<Long> {
        return httpClient.get("topstories.json").body()
    }

    override suspend fun getStory(storyId: String): Story {
        return httpClient.get("item/9129911.json").body()
    }
}