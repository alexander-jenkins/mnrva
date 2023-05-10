package edu.towson.cosc435.mnrva.ui.schedule


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.towson.cosc435.mnrva.data.Event
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleView(nav: NavHostController) {
    //TODO -- Get entry list from main and replace this test code
    val entries: MutableList<Event> = mutableListOf()
    for (x in 1..5) {
        entries.add(
            Event(
                "testid", "me", "title", null, LocalDateTime.now(), null, ""
            )

        )
    }

    entries.shuffle()
    entries.sortBy { r -> r.start }//SORT ENTRIES BY DATE

    val format: DateTimeFormatter = DateTimeFormatter.ofPattern("h a")
    var lastSeenHour: Int? = null

    LazyColumn(
        contentPadding = PaddingValues(bottom = 64.dp)
    ) {
        items(entries) { entry ->
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
            TaskCard(entry, nav)
        }
    }

}