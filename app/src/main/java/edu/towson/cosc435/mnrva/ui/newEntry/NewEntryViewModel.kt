package edu.towson.cosc435.mnrva.ui.newEntry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.Event
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class NewEntryViewModel : ViewModel() {
    private val eventRepository = DependencyGraph.eventRepository
    private val owner by DependencyGraph.settingsRepository.name

    private val _title = mutableStateOf("")
    val title = _title
    fun setTitle(title: String) {
        _title.value = title
    }

    private val _description = mutableStateOf("")
    val description = _description
    fun setDescription(description: String) {
        _description.value = description
    }

    private val _start = mutableStateOf("")
    val start = _start
    fun setStart(start: String) {
        _start.value = start
    }

    private val _end = mutableStateOf("")
    val end = _end
    fun setEnd(end: String) {
        _end.value = end
    }

    // should show the pickers
    private val _showStart = mutableStateOf(false)
    val showStart = _showStart
    fun toggleShowStart() {
        _showStart.value = !_showStart.value
    }

    private val _showEnd = mutableStateOf(false)
    val showEnd = _showEnd
    fun toggleShowEnd(value: Boolean) {
        _showEnd.value = value
    }

    fun createEvent() = viewModelScope.launch {
        if (_title.value != "") {
            val newEvent = Event(
                id = UUID.randomUUID().toString(),
                owner = owner!!,
                title = _title.value,
                description = _description.value,
                start = LocalDateTime.now(),
                end = null,
                tags = null
            )
            eventRepository.addEvent(newEvent)
            clearFields()
        }
    }

    private fun clearFields() {
        setTitle("")
        setDescription("")
        setStart("")
        setEnd("")
        _showStart.value = false
        _showEnd.value = false
    }
}