package edu.towson.cosc435.mnrva.ui.EditorDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.event.Event
import kotlinx.coroutines.launch

class EditorDialogViewModel: ViewModel() {

    private val eventRepository = DependencyGraph.eventRepository

    fun updateEvent(event: Event) {
        viewModelScope.launch {
            eventRepository.updateEvent(event)
        }


    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            eventRepository.deleteEvent(event)
        }
    }
}