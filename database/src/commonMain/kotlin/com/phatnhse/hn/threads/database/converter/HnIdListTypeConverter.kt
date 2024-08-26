package com.phatnhse.hn.threads.database.converter

import androidx.room.TypeConverter

class HnIdListTypeConverter {
    @TypeConverter
    fun toString(ids: List<Long>): String {
        return ids.joinToString(";")
    }

    @TypeConverter
    fun fromString(ids: String): List<Long> {
        return ids.split(";").map { it.toLong() }
    }
}