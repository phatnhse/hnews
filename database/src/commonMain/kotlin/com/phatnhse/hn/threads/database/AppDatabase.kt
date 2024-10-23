package com.phatnhse.hn.threads.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.phatnhse.hn.threads.database.converter.RoomListConverters
import com.phatnhse.hn.threads.database.dao.HackerNewsDao
import com.phatnhse.hn.threads.database.entity.HnewsItem

private const val DATABASE_VERSION = 1
const val DATABASE_NAME = "hnews"

@Database(
    entities = [HnewsItem::class],
    version = DATABASE_VERSION
)
@TypeConverters(RoomListConverters::class)
@ConstructedBy(MyConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hackerNewsDao(): HackerNewsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MyConstructor : RoomDatabaseConstructor<AppDatabase>