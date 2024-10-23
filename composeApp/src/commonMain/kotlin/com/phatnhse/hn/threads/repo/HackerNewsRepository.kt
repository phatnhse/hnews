package com.phatnhse.hn.threads.repo

import com.phatnhse.hn.threads.database.entity.HnewsItem
import kotlinx.coroutines.flow.Flow

interface HackerNewsRepository {

    suspend fun syncFeed()
    fun getTopStories(): Flow<List<HnewsItem>>
}
