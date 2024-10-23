package com.phatnhse.hn.threads.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phatnhse.hn.threads.database.entity.HnewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HackerNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<HnewsItem>)

    @Query("SELECT * FROM items")
    fun getTopStories(): Flow<List<HnewsItem>>

}