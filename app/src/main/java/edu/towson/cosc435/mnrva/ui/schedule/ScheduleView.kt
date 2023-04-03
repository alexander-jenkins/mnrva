package edu.towson.cosc435.mnrva.ui.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.*
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("ha")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleView(

){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 0..23){
            val timeIndex = LocalTime.of(i, 0)
            Text(text = timeIndex.format(formatter))
            TaskView_Schedule(
                time = timeIndex,
                description = "my Desc",
                tag = "my Tag",
                onTaskPress = {}
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
            .padding(all = 16.dp)
            .padding(start = 30.dp)
    )
    {
        Column() {
            Text(text = description)
            Row(horizontalArrangement = Arrangement.End) {
                Text(text = tag)
            }
        }
    }
}