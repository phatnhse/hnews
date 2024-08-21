package com.phatnhse.hn.threads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.news.datasource.HackerNewsRemoteDataSource
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.News
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val database: AppDatabase,
    private val newsNetworkDataSource: HackerNewsRemoteDataSource
) : ViewModel() {

    private val _ui: MutableStateFlow<List<News>> = MutableStateFlow(listOf())
    val ui: StateFlow<List<News>> = _ui.asStateFlow()

    private val _response: MutableStateFlow<String> = MutableStateFlow("")
    val response = _response.asStateFlow()

    var counter = 0

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.newsDao().insertNews(
                    listOf(
                        News(
                            1,
                            "Hello worlds"
                        ),
                        News(
                            2,
                            "Second news"
                        ),
                        News(
                            3,
                            "Third news"
                        )
                    )
                )
            }

            database.newsDao().getPlayerListAsFlow().collect {
                _ui.emit(it)
            }
        }


    }

    fun request() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = newsNetworkDataSource.getStory("")
                _response.emit(response.toString())
            }
        }
    }
}