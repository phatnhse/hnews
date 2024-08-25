package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.HnStoryResponse
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class HnItemId(val id: Long)

interface HnRemoteDataSource {
    suspend fun getTopStoriesConcurent(): List<HnStoryResponse>
}