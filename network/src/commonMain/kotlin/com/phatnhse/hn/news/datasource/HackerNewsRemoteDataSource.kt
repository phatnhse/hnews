package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.Story

interface HackerNewsRemoteDataSource {
    suspend fun getTopStories(): List<Long>
    suspend fun getStory(storyId: String): Story
}