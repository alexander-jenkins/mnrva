package edu.towson.cosc435.mnrva.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.towson.cosc435.mnrva.data.model.event.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    // Get a list of all events in the database
    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    // Insert a new event into the database; overwrite if the ID already exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    // Change the details of an event in the database
    @Update
    suspend fun update(event: Event)

    // Delete an event from the database
    @Delete
    suspend fun delete(event: Event)

    // Delete all events from the database; used when the user logs out of the application
    @Query("DELETE FROM EVENTS")
    suspend fun clearEvents()

    // Get the details of a specific event from the database
    @Query("SELECT * FROM events WHERE id = :id")
    suspend fun getEventById(id: String): Event?
}