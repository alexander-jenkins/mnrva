package edu.towson.cosc435.mnrva.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.Event
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.TaskCard
import java.time.LocalDateTime

@Composable
fun HomeView(nav: NavHostController, vm: EventViewModel = viewModel()) {
    val eventsList: List<Event> by vm.allEvents
    val name by DependencyGraph.settingsRepository.name

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Hello $name!", fontSize = 36.sp)
            Text("Here are your upcoming plans:", fontSize = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Text("This week:", fontSize = 15.sp)

            //Example Date for the following TaskCard
            val dateTime = LocalDateTime.of(2023, 4, 23, 12, 30, 0)

            val entry01 = Event(
                id = "0",
                owner = "Job Interview",
                title = "TEST",
                description = "interview with Apples and Oranges",
                start = LocalDateTime.of(2023, 4, 15, 0, 0, 0),
                end = null,
                tags = "Job Interview"
            )
            val entries = listOf(entry01, entry01, entry01, entry01)
            LazyColumn(contentPadding = PaddingValues(bottom = 48.dp)) {
                items(entries) {
                    TaskCard(
                        it
                    )
                }
            }
        }
    }
}