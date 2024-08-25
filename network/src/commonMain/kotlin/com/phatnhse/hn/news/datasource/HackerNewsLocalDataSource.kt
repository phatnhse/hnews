package com.phatnhse.hn.news.datasource

interface HackerNewsLocalDataSource {
    suspend fun saveNews()
}