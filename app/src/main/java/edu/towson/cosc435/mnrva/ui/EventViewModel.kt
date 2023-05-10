package edu.towson.cosc435.mnrva.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.data.Event
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class EventViewModel : ViewModel() {
    private val repository = DependencyGraph.eventRepository


    val allEvents = repository.events


    fun addEvent() = viewModelScope.launch {
        repository.addEvent(
            Event(
                UUID.randomUUID().toString(), "me", "title", null, LocalDateTime.now(), null, ""
            )
        )
    }

}