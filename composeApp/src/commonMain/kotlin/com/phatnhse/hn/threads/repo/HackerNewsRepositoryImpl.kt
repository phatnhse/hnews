package com.phatnhse.hn.threads.repo

import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.HackerNewItem
import com.phatnhse.hn.threads.database.entity.HackerNewsStory
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
            val items = topStories.map { story ->
                HackerNewItem(
                    id = story.id,
                    title = story.title,
                    url = story.url.orEmpty(),
                    score = story.score,
                    by = story.by,
                    time = story.time,
                    type = story.type,
                    descendants = story.descendants ?: 0,
                    kids = story.kids ?: emptyList(),
                    text = story.text.orEmpty()
                )
            }
            val stories = topStories.map { story ->
                HackerNewsStory(
                    id = 0,
                    itemId = story.id,
                    time = story.time
                )
            }
            appDatabase.hackerNewsDao().insertItems(items)
            appDatabase.hackerNewsDao().insertStories(stories)
        }
    }

    override fun getTopStories(): Flow<List<HackerNewItem>> {
        return appDatabase.hackerNewsDao().getTopStories(30)
            .flowOn(Dispatchers.IO)
    }
}
