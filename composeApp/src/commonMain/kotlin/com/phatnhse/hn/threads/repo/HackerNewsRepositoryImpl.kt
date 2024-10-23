package com.phatnhse.hn.threads.repo

import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.news.response.HnStoryResponse
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.HnewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class HackerNewsRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val hnRemoteDataSource: HnRemoteDataSource
) : HackerNewsRepository {

    override suspend fun syncFeed() {
        withContext(Dispatchers.IO) {
            val topStories = hnRemoteDataSource.getTopStories(30)
                .filter {
                    it.dead == true || it.deleted == true
                }
            val items = topStories.map { it.toHackerNewItem() }
            appDatabase.hackerNewsDao().insertItems(items)
        }
    }

    override fun getTopStories(): Flow<List<HnewsItem>> {
        return appDatabase.hackerNewsDao().getTopStories()
            .flowOn(Dispatchers.IO)
    }
}

private fun HnStoryResponse.toHackerNewItem() = HnewsItem(
    id = id,
    title = title,
    url = url.orEmpty(),
    score = score,
    by = by,
    time = time,
    type = type,
    descendants = descendants ?: 0,
    kids = kids ?: emptyList(),
    text = text.orEmpty()
)