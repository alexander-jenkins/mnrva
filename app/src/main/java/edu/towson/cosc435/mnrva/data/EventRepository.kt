package edu.towson.cosc435.mnrva.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import edu.towson.cosc435.mnrva.data.room.EventDao
import edu.towson.cosc435.mnrva.model.event.Event
import java.time.LocalDateTime

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
                sortedEvent.start.isAfter(LocalDateTime.now()) || (LocalDateTime.now()
                    .isBefore(sortedEvent.end)) && LocalDateTime.now()
                    .isAfter(sortedEvent.start)
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
}