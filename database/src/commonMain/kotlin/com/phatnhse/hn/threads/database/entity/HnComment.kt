package com.phatnhse.hn.threads.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "hn_comments",
    foreignKeys = [
        ForeignKey(
            entity = HnItem::class,
            parentColumns = ["id"],
            childColumns = ["parentId"]
        )
    ],
    indices = [Index("id", "parentId")]
)
data class HnComment(
    @PrimaryKey
    val id: Long,
    val parentId: Long,
    val by: String,
    val time: Long,
    val title: String,
    val score: Int,
    val descendants: Int = 0,
    val kids: List<Long> = emptyList(),
    val url: String = "",
)