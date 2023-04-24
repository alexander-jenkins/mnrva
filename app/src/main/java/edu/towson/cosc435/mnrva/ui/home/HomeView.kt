package edu.towson.cosc435.mnrva.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import edu.towson.cosc435.mnrva.model.Entry
import edu.towson.cosc435.mnrva.ui.nav.Routes
import edu.towson.cosc435.mnrva.ui.theme.Envy
import edu.towson.cosc435.mnrva.ui.theme.FringyFlower
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(nav: NavHostController) {

    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Hello Friend!", fontSize = 36.sp)
            Text("Here are your upcoming plans:", fontSize = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Text("This week:", fontSize = 15.sp)

            //Example Date for the following TaskCard
            val date = LocalDate.of(2023, Month.APRIL, 2)
            val time = LocalTime.of(12, 30, 0)
            val dateTime = LocalDateTime.of(date, time)

            val entry01 = Entry(
                0,
                "Job Interview",
                date = LocalDateTime.of(2023, 4, 15, 0, 0),
                startTime = null,
                endTime = null,
                description = "interview with Apples and Oranges",
                tag = "Job Interview"
            )
            val entries = listOf(entry01, entry01, entry01, entry01)
            LazyColumn(contentPadding = PaddingValues(bottom = 48.dp)) { items(entries) { TaskCard(it, nav) } }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskCard(entry: Entry, nav: NavHostController) {
    val horizontalGradientBrush = Brush.horizontalGradient(colors = listOf(FringyFlower, Envy))
    val formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm", Locale.ENGLISH)

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 16.dp,
        onClick = { nav.navigate(Routes.NewEntryView.route) },
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp).fillMaxWidth()
    ) {
        Box(modifier = Modifier.background(brush = horizontalGradientBrush)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(modifier = Modifier.weight(1.5f)) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(entry.title, fontSize = 36.sp, modifier = Modifier.weight(1.0f))
                    }
                    if (entry.description != null) {
                        Row(
                            modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically
                        ) { Text(entry.description, fontSize = 18.sp, modifier = Modifier.weight(1.0f)) }
                    }

                    Row(
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) { Text(entry.date.format(formatter), modifier = Modifier.weight(1.0f)) }
                    Row(horizontalArrangement = Arrangement.End) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Card(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier.background(Color(25)).padding(bottom = 10.dp)
                        ) { Text(entry.tag, modifier = Modifier.weight(1.0f).padding(all = 2.dp)) }
                    }
                }
            }
        }
    }
}