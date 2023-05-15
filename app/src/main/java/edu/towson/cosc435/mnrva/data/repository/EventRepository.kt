package edu.towson.cosc435.mnrva.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import edu.towson.cosc435.mnrva.data.model.event.Event
import edu.towson.cosc435.mnrva.data.model.event.SyncedEventResponse
import edu.towson.cosc435.mnrva.data.room.EventDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventRepository(private val eventDao: EventDao) {
    // Hold all events in memory
    private val _events: MutableState<List<Event>> = mutableStateOf(listOf())
    val events: State<List<Event>> = _events

    // Hold today's events in memory
    private val _todayEvents: MutableState<List<Event>> = mutableStateOf(listOf())
    val todayEvents: State<List<Event>> = _todayEvents

    // Hold the next three events in memory
    private val _nextThree: MutableState<List<Event>> = mutableStateOf(listOf())
    val nextThree: State<List<Event>> = _nextThree

    // Listen to the database; triggers a callback function on update
    private val liveEventList: LiveData<List<Event>> = eventDao.getEvents().asLiveData()

    init {
        // Register a callback to the live event list
        liveEventList.observeForever { allEvents ->

            // copy all events to memory on change
            _events.value = allEvents.sortedBy { events -> events.start }

            // copy today's events to memory on change
            _todayEvents.value = allEvents.sortedBy { events -> events.start }
                .filter { sortedEvent -> LocalDateTime.now().dayOfYear == sortedEvent.start.dayOfYear && sortedEvent.start.year == LocalDateTime.now().year }

            // copy the next three events to memory on change
            _nextThree.value = allEvents.sortedBy { events -> events.start }.filter { sortedEvent ->
                sortedEvent.start.isAfter(LocalDateTime.now().minusMinutes(30))
            }.take(3)
        }
    }

    // Insert a new event into the SQLite database
    suspend fun addEvent(event: Event) {
        eventDao.insert(event)
    }

    // Delete all events from the SQLite database; used when the user logs out of the app
    suspend fun clearEvents() {
        eventDao.clearEvents()
    }

    // Change the details of a specific event in the SQLite database
    suspend fun updateEvent(event: Event) {
        eventDao.update(event)
    }

    // Delete an event from the SQLite database
    suspend fun deleteEvent(event: Event) {
        eventDao.delete(event)
    }

    // Get the details of a specific event from the SQLite database
    suspend fun getById(id: String): Event? {
        return eventDao.getEventById(id)
    }

    // Insert events downloaded from the API into the SQLite database
    suspend fun addManyEvents(responseList: List<SyncedEventResponse>) {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        responseList.map { event ->
            eventDao.insert(
                Event(
                    id = event.id,
                    title = event.title,
                    description = event.description.orEmpty(),
                    start = LocalDateTime.from(formatter.parse(event.start)),
                    end = if (event.end.orEmpty() != "") LocalDateTime.from(formatter.parse(event.end)) else null,
                    tags = event.tags.orEmpty()
                )
            )
        }
    }
}