package edu.towson.cosc435.mnrva.ui.newEntry

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.Event
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class NewEntryViewModel : ViewModel() {
    private val eventRepository = DependencyGraph.eventRepository
    private val owner by DependencyGraph.settingsRepository.name

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d',' y 'at' h:mm a")

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

    private val _start = mutableStateOf(LocalDateTime.now())
    val start = _start
    private fun setStart(start: LocalDateTime) {
        _start.value = start
    }


    fun setStart(picker: DatePicker, year: Int, month: Int, day: Int) {
        setStart(LocalDateTime.of(year, month + 1, day, _start.value.hour, _start.value.minute))
    }

    fun setStart(picker: TimePicker, hour: Int, minute: Int) {
        setStart(
            LocalDateTime.of(
                _start.value.year, _start.value.month, _start.value.dayOfMonth, hour, minute
            )
        )
    }

    private val _end = mutableStateOf(LocalDateTime.now())
    val end = _end
    private fun setEnd(end: LocalDateTime) {
        _end.value = end
    }

    fun setEnd(view: DatePicker, year: Int, month: Int, day: Int) {
        setEnd(LocalDateTime.of(year, month + 1, day, _end.value.hour, _end.value.minute))
    }

    fun setEnd(view: TimePicker?, hour: Int, minute: Int) {
        setEnd(
            LocalDateTime.of(
                _start.value.year, _start.value.month, _start.value.dayOfMonth, hour, minute
            )
        )
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
                start = start.value,
                end = end.value,
                tags = null
            )
            eventRepository.addEvent(newEvent)
            clearFields()
        }
    }

    private fun clearFields() {
        setTitle("")
        setDescription("")
        setStart(LocalDateTime.now())
        setEnd(LocalDateTime.now())
        _showStart.value = false
        _showEnd.value = false
    }
}