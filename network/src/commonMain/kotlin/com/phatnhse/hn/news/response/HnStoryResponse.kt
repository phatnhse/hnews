package com.phatnhse.hn.news.response

import kotlinx.serialization.Serializable

/**
 * Specs: https://github.com/HackerNews/API
 */
@Serializable
data class HnStoryResponse(
    val id: Long,
    val by: String,
    val time: Long,
    val title: String,
    val score: Int,
    val type: String,
    val descendants: Int? = null,
    val kids: List<Long>? = null,
    val url: String? = null,
    val text: String? = null,
    val deleted: Boolean? = null,
    val dead: Boolean? = null
)