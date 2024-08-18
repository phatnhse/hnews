package com.phatnhse.hn.threads

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform