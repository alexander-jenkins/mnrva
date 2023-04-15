package edu.towson.cosc435.mnrva.ui.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.mnrva.model.Entry
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import java.time.*
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("ha")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleView(
onTaskPress: (Entry) -> Unit
){
    //TODO -- Get entry list from main and replace this test code
    var entries: List<Entry> = emptyList()
    for (x in 1..23){
        entries += Entry(x,
            "A01",
            date =LocalDateTime.of(2023, 4, 15, x, 0),
            start_time = LocalDateTime.of(2023, 4, 15, x, 0),
            end_time = null,
            description = "interview with $x",
            tag = "tag $x"
        )
        entries += Entry(x,
            "B01",
            date =LocalDateTime.of(2023, 4, 15, x, 0),
            start_time = LocalDateTime.of(2023, 4, 15, x, 0),
            end_time = null,
            description = "interview with $x",
            tag = "tag $x"
        )
    }

    entries += Entry(101,
        "C01",
        date =LocalDateTime.of(2023, 4, 15, 5, 0),
        start_time = LocalDateTime.of(2023, 4, 15, 5, 0),
        end_time = null,
        description = "Inserted Element",
        tag = "HELlO WORLD"
    )

    entries = entries.shuffled()
    Log.d("TEST", entries.toString())
    entries = entries.sortedBy { r -> r.date}//SORT ENTRIES BY DATE
    Log.d("TEST", entries.toString())

    val format: DateTimeFormatter = DateTimeFormatter.ofPattern("h a")

    var lastSeenHour: Int? = null

    LazyColumn(
        contentPadding = PaddingValues(bottom = 64.dp)
    ){
        items(entries) { entry ->
            //Display the hour for the next taskcards and keep track of when
            //to draw the next "hour" marker
            if (entry.date.hour != lastSeenHour){
                lastSeenHour = entry.date.hour
                Text(
                    text = "${entry.start_time?.format(format)}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500)
                )
            }

            //Draw the taskcard
            TaskCard(
                entry,
                onClick = {r -> onTaskPress(r)}
            )
        }
    }

}
@Composable
fun TaskView_Schedule(
    time: LocalTime,
    description: String,
    tag: String,
    onTaskPress: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(all = 16.dp)
            .padding(start = 30.dp)
            .clickable {
                onTaskPress
            }
    )
    {
        Column() {
            Text(
                modifier = Modifier.padding(all=8.dp),
                text = description
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = tag)
            }
        }
    }
}