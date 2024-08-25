package com.phatnhse.hn.threads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class AppViewModel(
    private val newsNetworkDataSource: HnRemoteDataSource
) : ViewModel() {

    private val _response: MutableStateFlow<String> = MutableStateFlow("")
    val response = _response.asStateFlow()

    private val _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    fun request() {

    }

    fun request1() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _showProgressBar.emit(true)
                val a = Clock.System.now()
                val response = newsNetworkDataSource.getTopStoriesConcurent()
                val b = Clock.System.now()
                _response.emit((b - a).toString() + " " + response.toString().take(20))
                _showProgressBar.emit(false)

            }
        }
    }
}