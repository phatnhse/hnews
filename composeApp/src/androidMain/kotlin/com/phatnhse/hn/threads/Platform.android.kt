package com.phatnhse.hn.threads

import android.os.Build
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.phatnhse.hn.threads.database.AppDatabase
import com.phatnhse.hn.threads.database.DATABASE_NAME
import kotlinx.coroutines.Dispatchers

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getDatabase(): AppDatabase {
    val dbFile = Application.context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(Application.context, AppDatabase::class.java, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}