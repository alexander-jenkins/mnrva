package edu.towson.cosc435.mnrva.ui.EntryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.mnrva.model.Event
import edu.towson.cosc435.mnrva.ui.taskCard.TaskCard
import java.time.LocalDate

@Composable
fun EntryList(entriesToShow: List<Event>, date: LocalDate?){
    Column(
        Modifier
            .padding(all=2.dp)
            .fillMaxHeight()
    ) {
//        var displayText = ""
        val displayText = if (date == null){
            "Scheduled Events"
        } else{
            "Scheduled Events on ${date.monthValue}/${date.dayOfMonth}"
        }
        Text(
            text = displayText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(500),
        )
        LazyColumn(
            contentPadding = PaddingValues(bottom = 64.dp)
        ) {
            items(entriesToShow) {
                TaskCard(it)
            }
        }
    }
}