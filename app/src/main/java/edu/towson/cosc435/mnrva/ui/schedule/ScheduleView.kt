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
import edu.towson.cosc435.mnrva.model.Entry
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("ha")

@Composable
fun ScheduleView(nav: NavHostController) {
    //TODO -- Get entry list from main and replace this test code
    val entries: MutableList<Entry> = mutableListOf()
    for (x in 1..23) {
        entries.add(
            Entry(
                x,
                "A01",
                date = LocalDateTime.of(2023, 4, 15, x, 0),
                startTime = LocalDateTime.of(2023, 4, 15, x, 0),
                endTime = null,
                description = "interview with $x",
                tag = "tag $x"
            )
        )
        entries.add(
            Entry(
                x,
                "B01",
                date = LocalDateTime.of(2023, 4, 15, x, 0),
                startTime = LocalDateTime.of(2023, 4, 15, x, 0),
                endTime = null,
                description = "interview with $x",
                tag = "tag $x"
            )
        )
    }
    entries.add(
        Entry(
            101,
            "C01",
            date = LocalDateTime.of(2023, 4, 15, 5, 0),
            startTime = LocalDateTime.of(2023, 4, 15, 5, 0),
            endTime = null,
            description = "Inserted Element",
            tag = "HELlO WORLD"
        )
    )

    entries.shuffle()
    entries.sortBy { r -> r.date }//SORT ENTRIES BY DATE

    val format: DateTimeFormatter = DateTimeFormatter.ofPattern("h a")
    var lastSeenHour: Int? = null

    LazyColumn(
        contentPadding = PaddingValues(bottom = 64.dp)
    ) {
        items(entries) { entry ->
            //Display the hour for the next task cards and keep track of when
            //to draw the next "hour" marker
            if (entry.date.hour != lastSeenHour) {
                lastSeenHour = entry.date.hour
                Text(
                    text = "${entry.startTime?.format(format)}",
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