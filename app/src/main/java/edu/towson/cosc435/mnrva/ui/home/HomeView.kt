package edu.towson.cosc435.mnrva.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import edu.towson.cosc435.mnrva.data.Event
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.nav.Routes
import edu.towson.cosc435.mnrva.ui.theme.Envy
import edu.towson.cosc435.mnrva.ui.theme.FringyFlower
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun HomeView(nav: NavHostController, vm: EventViewModel = viewModel()) {
    val eventsList: List<Event> by vm.allEvents

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Hello Friend!", fontSize = 36.sp)
            Text("Here are your upcoming plans:", fontSize = 15.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("This week:", fontSize = 15.sp)
            LazyColumn(contentPadding = PaddingValues(bottom = 48.dp)) {
                items(eventsList) { event ->
                    TaskCard(event, nav)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(entry: Event, nav: NavHostController) {
    val horizontalGradientBrush = Brush.horizontalGradient(colors = listOf(FringyFlower, Envy))
    val formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm", Locale.ENGLISH)

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 16.dp,
        onClick = { nav.navigate(Routes.NewEntryView.route) },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.background(brush = horizontalGradientBrush)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(modifier = Modifier.weight(1.5f)) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(entry.title, fontSize = 36.sp, modifier = Modifier.weight(1.0f))
                    }
                    if (entry.description != null) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                entry.description,
                                fontSize = 18.sp,
                                modifier = Modifier.weight(1.0f)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) { Text(entry.start.format(formatter), modifier = Modifier.weight(1.0f)) }
                    Row(horizontalArrangement = Arrangement.End) {
                        Spacer(modifier = Modifier.padding(5.dp))

                        if (entry.tags != null)
                            Card(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .background(Color(25))
                                    .padding(bottom = 10.dp)
                            ) {
                                Text(
                                    entry.tags,
                                    modifier = Modifier
                                        .weight(1.0f)
                                        .padding(all = 2.dp)
                                )
                            }
                    }
                }
            }
        }
    }
}