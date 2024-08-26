package com.phatnhse.hn.threads.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.phatnhse.hn.threads.database.converter.HnIdListTypeConverter
import com.phatnhse.hn.threads.database.dao.HnItemDao
import com.phatnhse.hn.threads.database.entity.HnComment
import com.phatnhse.hn.threads.database.entity.HnItem

private const val DATABASE_VERSION = 1
const val DATABASE_NAME = "hn_threads"

@Database(
    entities = [HnItem::class, HnComment::class],
    version = DATABASE_VERSION
)
@TypeConverters(HnIdListTypeConverter::class)
abstract class AppDatabase : RoomDatabase(), ClearAllTables {

    abstract fun hnItemDao(): HnItemDao

    override fun clearAllTables() {
        //
    }
}

interface ClearAllTables {
    fun clearAllTables()
}