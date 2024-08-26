package com.phatnhse.hn.threads.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phatnhse.hn.threads.database.dao.NewsDao
import com.phatnhse.hn.threads.database.entity.News

private const val DATABASE_VERSION = 1
const val DATABASE_NAME = "hn_threads"

@Database(entities = [News::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(), ClearAllTables {

    abstract fun newsDao(): NewsDao

    override fun clearAllTables() {
        //
    }
}

interface ClearAllTables {
    fun clearAllTables()
}