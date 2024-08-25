package com.phatnhse.hn.news.response

enum class HnItemType(val type: String) {
    STORY("story"),
    COMMENT("comment"),
    JOB("job"),
    ASK("ask"),
    POLL("poll"),
    POLLOPT("pollopt");

    companion object {
        fun fromString(type: String): HnItemType? {
            return entries.find { it.type == type }
        }
    }
}