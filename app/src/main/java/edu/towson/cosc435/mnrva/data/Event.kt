package edu.towson.cosc435.mnrva.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity("events")
data class Event(
    @PrimaryKey @SerializedName("_id") val id: String,
    @ColumnInfo @SerializedName("owner_id") val owner: String,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String?,
    @ColumnInfo val start: LocalDateTime,
    @ColumnInfo val end: LocalDateTime?,
    @ColumnInfo val tags: String?,
)