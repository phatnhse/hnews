package com.phatnhse.hn.threads

import com.phatnhse.hn.threads.database.AppDatabase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getDatabase(): AppDatabase