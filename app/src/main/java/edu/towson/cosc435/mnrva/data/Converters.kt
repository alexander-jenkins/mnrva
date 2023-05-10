package edu.towson.cosc435.mnrva.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            LocalDateTime.from(formatter.parse(value))
        }
    }

}