package edu.towson.cosc435.mnrva.data.room

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    // Convert the LocalDateTime object to a string for SQLite compatibility
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return value?.toString()
    }

    // Convert the String object to a LocalDateTime object for Kotlin compatibility
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            LocalDateTime.from(formatter.parse(value))
        }
    }
}