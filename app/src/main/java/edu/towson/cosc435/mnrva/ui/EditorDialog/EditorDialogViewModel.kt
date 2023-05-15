package edu.towson.cosc435.mnrva.ui.EditorDialog

import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.event.Event
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditorDialogViewModel : ViewModel() {
    private val eventRequests = DependencyGraph.eventRequests
    private val repository = DependencyGraph.eventRepository

    // formatting for displaying the datetime objects
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/d")
    val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")
    val startFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d',' y 'at' h:mm a")
    val endFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("'at' h:mm a")

    private val _showEditor = mutableStateOf(false)
    val showEditor = _showEditor
    fun setShowEditor(state: Boolean) {
        _showEditor.value = state
    }

    private val _selectedEvent: MutableState<Event?> = mutableStateOf(null)
    val selectedEvent = _selectedEvent
    fun setSelected(id: String) {
        viewModelScope.launch {
            _selectedEvent.value = repository.getById(id)

            val event = _selectedEvent.value
            if (event != null) {
                setTitle(event.title)
                setDescription(event.description)
                setStart(event.start)
                setEnd(event.end)
                setTags(event.tags)
                setShowEditor(true)
            }
        }
    }

    private val _title: MutableState<String> = mutableStateOf("")
    val title = _title
    fun setTitle(title: String) {
        _title.value = title
    }

    private val _description: MutableState<String?> = mutableStateOf(null)
    val description = _description
    fun setDescription(description: String?) {
        _description.value = description
    }

    private val _start: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())
    val start = _start
    private fun setStart(time: LocalDateTime) {
        _start.value = time
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

    private val _end: MutableState<LocalDateTime?> = mutableStateOf(null)
    val end = _end
    private fun setEnd(time: LocalDateTime?) {
        _end.value = time
    }

    fun setEnd(view: TimePicker?, hour: Int, minute: Int) {
        setEnd(
            LocalDateTime.of(
                _start.value.year, _start.value.month, _start.value.dayOfMonth, hour, minute
            )
        )
    }

    private val _tags: MutableState<String?> = mutableStateOf(null)
    val tags = _tags
    fun setTags(tags: String?) {
        _tags.value = tags
    }

    fun updateEvent() {
        viewModelScope.launch {
            val event = _selectedEvent.value
            if (event != null && _title.value != "") {
                Log.d("MNRVA", "Editing ${event.id}")
                eventRequests.updateEvent(
                    Event(
                        id = event.id,
                        title = _title.value,
                        description = _description.value,
                        start = _start.value,
                        end = _end.value,
                        tags = _tags.value,
                    )
                )
            }
            setShowEditor(false)
        }
    }

    fun deleteEvent() {
        viewModelScope.launch {
            val event = _selectedEvent.value
            if (event != null) eventRequests.deleteEvent(event.id)
            setShowEditor(false)
        }
    }
}