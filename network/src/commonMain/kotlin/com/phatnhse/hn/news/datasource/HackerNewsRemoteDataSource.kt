package com.phatnhse.hn.news.datasource

import com.phatnhse.hn.news.response.Story

interface HackerNewsRemoteDataSource {
    suspend fun getTopStories(): List<Story>
    suspend fun getStory(storyId: String): Story
}