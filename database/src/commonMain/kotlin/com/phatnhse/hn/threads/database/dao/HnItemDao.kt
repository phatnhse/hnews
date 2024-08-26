package com.phatnhse.hn.threads.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phatnhse.hn.threads.database.entity.HnComment
import com.phatnhse.hn.threads.database.entity.HnItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HnItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHnItems(teamList: List<HnItem>)

    @Query("SELECT * FROM hn_items")
    suspend fun getHnItems(): List<HnItem>

    @Query("SELECT * FROM hn_comments WHERE parentId = :parentId")
    suspend fun getHnComments(parentId: Long): List<HnComment>

}