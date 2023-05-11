package edu.towson.cosc435.mnrva.ui.calenderView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.mnrva.model.Event
import java.time.LocalDate

class CalendarViewModel : ViewModel(){
    var entries by mutableStateOf(emptyList<Event>())
    var entriesToShow: List<Event> by mutableStateOf(emptyList())
    var selectedDay: LocalDate? by mutableStateOf(null)
}