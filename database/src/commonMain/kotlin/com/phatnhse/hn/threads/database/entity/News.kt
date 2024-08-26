package com.phatnhse.hn.threads.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey val id: Long,
    val title: String,
)