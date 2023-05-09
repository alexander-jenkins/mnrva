package edu.towson.cosc435.mnrva.data.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import edu.towson.cosc435.mnrva.data.Converters
import edu.towson.cosc435.mnrva.model.Event
import java.time.LocalDateTime

@Dao
interface EventsDao {
    @Query("select * from events")
    suspend fun getAll(): List<Event>

    @Insert
    suspend fun addEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)
}

@Database(entities = [Event::class], version = 1)
@TypeConverters(Converters::class)
abstract class MnrvaLocalDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}

suspend fun addRandomEvent(dao: EventsDao) {
    dao.addEvent(
        Event(
            owner = "Test Owner",
            id = java.util.UUID.randomUUID().toString(),
            title = "Test event",
            description = "",
            start = LocalDateTime.now(),
            end = null,
            tags = "A tag",
        )
    )
}