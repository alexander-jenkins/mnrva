package edu.towson.cosc435.mnrva.data

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

    private val _events: MutableState<List<Event>> = mutableStateOf(listOf())
    val events: State<List<Event>> = _events

    private val _todayEvents: MutableState<List<Event>> = mutableStateOf(listOf())
    val todayEvents: State<List<Event>> = _todayEvents

    private val _nextThree: MutableState<List<Event>> = mutableStateOf(listOf())
    val nextThree: State<List<Event>> = _nextThree

    private val liveEventList: LiveData<List<Event>> = eventDao.getEvents().asLiveData()

    init {
        liveEventList.observeForever { allEvents ->
            _events.value = allEvents.sortedBy { events -> events.start }
            _todayEvents.value = allEvents.sortedBy { events -> events.start }
                .filter { sortedEvent -> LocalDateTime.now().dayOfYear == sortedEvent.start.dayOfYear && sortedEvent.start.year == LocalDateTime.now().year }
            _nextThree.value = allEvents.sortedBy { events -> events.start }.filter { sortedEvent ->
                sortedEvent.start.isAfter(LocalDateTime.now().minusMinutes(30))
            }.take(3)
        }
    }

    suspend fun addEvent(event: Event) {
        eventDao.insert(event)
    }

    suspend fun clearEvents() {
        eventDao.clearEvents()
    }

    suspend fun updateEvent(event: Event) {
        eventDao.update(event)
    }

    suspend fun deleteEvent(event: Event) {
        eventDao.delete(event)
    }

    suspend fun getById(id: String): Event? {
        return eventDao.getEventById(id)
    }

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