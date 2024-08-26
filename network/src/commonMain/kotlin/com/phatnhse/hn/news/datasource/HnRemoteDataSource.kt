package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.HnStoryResponse
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class HnItemId(val value: Long)

interface HnRemoteDataSource {
    suspend fun getTopStories(): List<HnStoryResponse>
}