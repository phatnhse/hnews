package com.phatnhse.hn.threads.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.phatnhse.hn.threads.Application
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.DATABASE_NAME
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> { getDatabase() }
    }

actual fun getDatabase(): AppDatabase {
    val dbFile = Application.context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(Application.context, AppDatabase::class.java, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}