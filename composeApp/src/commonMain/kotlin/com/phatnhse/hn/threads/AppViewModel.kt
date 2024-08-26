package com.phatnhse.hn.threads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.news.datasource.HnRemoteDataSource
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.HnItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val getTopStoriesUseCase: GetTopStoriesUseCase,
) : ViewModel() {

    private val _response: MutableStateFlow<List<HnItem>> = MutableStateFlow(emptyList())
    val response = _response.asStateFlow()

    private val _showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _showProgressBar.emit(true)
                val response = getTopStoriesUseCase.execute()
                _response.emit(response)
                _showProgressBar.emit(false)
            }
        }
    }
}