package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.HnStoryResponse
import com.phatnhse.hn.news.util.retryFetch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class HnRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : HnRemoteDataSource {

    companion object {
        private const val PER_PAGE = 20
    }

    override suspend fun getTopStories(): List<HnStoryResponse> {
        return retryFetch {
            getTopStoriesConcurrent()
        } ?: emptyList()
    }

    private suspend fun getTopStoriesConcurrent(): List<HnStoryResponse> = coroutineScope {
        val topStoryIds = getTopStoryIds()

        topStoryIds.take(PER_PAGE).map { itemId ->
            async { getStoryDetails(itemId) }
        }.awaitAll()
    }

    private suspend fun getStoryDetails(storyId: HnItemId): HnStoryResponse {
        return httpClient.get("item/${storyId.id}.json").body()
    }

    private suspend fun getTopStoryIds(): List<HnItemId> {
        return httpClient.get("topstories.json").body()
    }
}