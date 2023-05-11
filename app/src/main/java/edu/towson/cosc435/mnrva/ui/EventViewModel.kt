package edu.towson.cosc435.mnrva.ui

import androidx.lifecycle.ViewModel
import edu.towson.cosc435.mnrva.DependencyGraph

class EventViewModel : ViewModel() {
    private val repository = DependencyGraph.eventRepository
    val allEvents = repository.events
}