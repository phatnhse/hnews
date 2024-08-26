package com.phatnhse.hn.threads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val newsNetworkDataSource: HnRemoteDataSource,
    private val database: AppDatabase,
) : ViewModel() {

    private val _response: MutableStateFlow<String> = MutableStateFlow("")
    val response = _response.asStateFlow()

    private val _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _showProgressBar.emit(true)
                val response = newsNetworkDataSource.getTopStories()
                val news = response.map {
                    News(
                        id = it.id.value,
                        title = it.title
                    )
                }
                database.newsDao().insertNews(news)
                val a = database.newsDao().getPlayerListAsFlow().first()
                _response.emit(a.toString())
                _showProgressBar.emit(false)
            }
        }
    }
}