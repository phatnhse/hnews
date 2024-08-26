package com.phatnhse.hn.threads.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.AppDatabase_Impl
import com.phatnhse.hn.threads.database.DATABASE_NAME
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual val platformModule: Module
    get() = module {
        single { getDatabase() }
    }

actual fun getDatabase(): AppDatabase {
    return createRoomDatabase()
}

fun createRoomDatabase(): AppDatabase {
    val dbFile = "${fileDirectory()}/$DATABASE_NAME"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory = { AppDatabase_Impl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

@OptIn(ExperimentalForeignApi::class)
private fun fileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path!!
}