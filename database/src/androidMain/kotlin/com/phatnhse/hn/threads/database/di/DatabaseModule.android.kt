package com.phatnhse.hn.threads.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.startup.Initializer
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.DATABASE_NAME
import kotlinx.coroutines.Dispatchers

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return getDataBase(applicationContext)
}

internal lateinit var applicationContext: Context
    private set

internal object EmptyContextObject

internal class KLocationInitializer : Initializer<EmptyContextObject> {
    override fun create(context: Context): EmptyContextObject {
        applicationContext = context.applicationContext
        return EmptyContextObject
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}

private fun getDataBase(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(context, AppDatabase::class.java, dbFile.absolutePath)
}