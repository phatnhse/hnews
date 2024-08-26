package com.phatnhse.hn.news.response

import com.phatnhse.hn.news.datasource.HnItemId
import kotlinx.serialization.Serializable

@Serializable
data class HnStoryResponse(
    val id: HnItemId,                   // Required: The item's unique id.
    val by: String,                     // Required: The username of the item's author.
    val time: Long,                     // Required: Creation date of the item, in Unix Time.
    val title: String,                  // Required: The title of the story. HTML.
    val score: Int,                     // Required: The story's score.
    val type: String,                   // Required: The type of item. Always "story" for this class.
    val descendants: Int? = null,       // Optional: The total comment count.
    val kids: List<HnItemId>? = null,   // Optional: The ids of the item's comments, in ranked display order.
    val url: String? = null,            // Optional: The URL of the story.
    val text: String? = null,           // Optional: The comment, story or poll text. HTML.
    val deleted: Boolean? = null,       // Optional: True if the item is deleted.
    val dead: Boolean? = null           // Optional: True if the item is dead.
)