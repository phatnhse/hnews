package com.phatnhse.hn.threads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.news.datasource.HackerNewsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val newsNetworkDataSource: HackerNewsRemoteDataSource
) : ViewModel() {

    private val _response: MutableStateFlow<String> = MutableStateFlow("")
    val response = _response.asStateFlow()

    private val _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    fun request() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _showProgressBar.emit(true)
                val response = newsNetworkDataSource.getStory("")
                _response.emit(response.toString())
                _showProgressBar.emit(false)
            }
        }
    }

    fun request1() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _showProgressBar.emit(true)
                val response = newsNetworkDataSource.getTopStories()
                _response.emit(response.toString())
                _showProgressBar.emit(false)
            }
        }
    }
}