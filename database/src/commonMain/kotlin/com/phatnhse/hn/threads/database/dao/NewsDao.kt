package com.phatnhse.hn.threads.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phatnhse.hn.threads.database.entity.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(teamList: List<News>)

    @Query("SELECT * FROM News")
    fun getPlayerListAsFlow(): Flow<List<News>>
}