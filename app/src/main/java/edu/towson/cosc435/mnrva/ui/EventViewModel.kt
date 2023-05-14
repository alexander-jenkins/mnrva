package edu.towson.cosc435.mnrva.ui

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.mnrva.DependencyGraph

class EventViewModel : ViewModel() {
    private val repository = DependencyGraph.eventRepository
    val allEvents by repository.events
    val nextThree by repository.nextThree
    val todayEvents by repository.todayEvents
}