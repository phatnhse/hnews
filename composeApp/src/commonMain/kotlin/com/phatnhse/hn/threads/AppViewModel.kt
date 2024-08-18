package com.phatnhse.hn.threads

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.entity.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val database: AppDatabase
) : ViewModel() {

    private val _ui: MutableStateFlow<List<News>> = MutableStateFlow(listOf())
    val ui: StateFlow<List<News>> = _ui.asStateFlow()

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
}