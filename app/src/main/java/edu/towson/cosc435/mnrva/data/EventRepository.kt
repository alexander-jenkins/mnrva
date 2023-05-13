package edu.towson.cosc435.mnrva.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import edu.towson.cosc435.mnrva.data.room.EventDao
import edu.towson.cosc435.mnrva.model.Event

class EventRepository(private val eventDao: EventDao) {

    private val _events: MutableState<List<Event>> = mutableStateOf(listOf())
    val events: State<List<Event>> = _events

    private val liveEventList: LiveData<List<Event>> = eventDao.getEvents().asLiveData()

    init {
        liveEventList.observeForever { _events.value = it }
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
}