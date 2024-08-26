package com.phatnhse.hn.threads

import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.HnItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface GetTopStoriesUseCase {
    suspend fun execute(): List<HnItem>
}

class GetTopStoriesUseCaseImpl(
    private val remoteDataSource: HnRemoteDataSource,
    private val database: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher,
) : GetTopStoriesUseCase {

    override suspend fun execute(): List<HnItem> {
        return withContext(ioDispatcher) {
            val topStories = remoteDataSource.getTopStories()
            val local = topStories.map {
                HnItem(
                    id = it.id,
                    by = it.by,
                    score = it.score,
                    time = it.time,
                    title = it.title,
                    type = it.type,
                    descendants = it.descendants ?: 0,
                    kids = it.kids ?: emptyList(),
                    url = it.url ?: "",
                )
            }
            database.hnItemDao().insertHnItems(local)
            local
        }
    }

}