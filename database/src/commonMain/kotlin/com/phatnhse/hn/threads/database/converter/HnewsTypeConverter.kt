package com.phatnhse.hn.threads.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RoomListConverters {

    @TypeConverter
    fun fromListString(value: List<Long>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toListString(value: String): List<Long> {
        return Json.decodeFromString<List<Long>>(value)
    }
}
