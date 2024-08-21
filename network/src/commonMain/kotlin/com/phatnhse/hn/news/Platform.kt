package com.phatnhse.hn.news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform