package edu.towson.cosc435.mnrva.ui.schedule


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.mnrva.model.event.Event
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditorDialogViewModel
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.taskCard.TaskCard
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleView(eventVM: EventViewModel, editorVM: EditorDialogViewModel) {
    val eventsList: List<Event> = eventVM.todayEvents

    val format: DateTimeFormatter = DateTimeFormatter.ofPattern("h a")
    var lastSeenHour: Int? = null
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(eventsList) { entry ->
            //Display the hour for the next task cards and keep track of when
            //to draw the next "hour" marker
            if (entry.start.hour != lastSeenHour) {
                lastSeenHour = entry.start.hour
                Text(
                    text = entry.start.format(format),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500)
                )
            }

            //Draw the task card
            TaskCard(entry, editorVM::setSelected)
        }
    }

}