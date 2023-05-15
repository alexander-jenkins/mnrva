package edu.towson.cosc435.mnrva.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.event.Event
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditorDialogViewModel
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.taskCard.TaskCard

@Composable
fun HomeView(eventVM: EventViewModel, editorVM: EditorDialogViewModel) {
    val eventsList: List<Event> = eventVM.nextThree
    val name by DependencyGraph.settingsRepository.name
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Hello $name!", fontSize = 36.sp)
            Text("Here are your next 3 events:", fontSize = 15.sp)
        }
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(eventsList) { event -> TaskCard(event, editorVM::setSelected) }
        }
    }
}