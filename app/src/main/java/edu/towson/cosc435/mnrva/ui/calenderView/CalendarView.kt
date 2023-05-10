package edu.towson.cosc435.mnrva.ui.calenderView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.data.Event
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate
import java.time.LocalDateTime


@Composable
fun Calendar(nav: NavHostController) {

    val entry = Event(
        id = "0",
        owner = "me",
        title = "Job Interview",
        start = LocalDateTime.of(2023, 4, 26, 23, 1),
        end = null,
        description = "interview with the best company ever",
        tags = "my tag"
    )

    val entries = listOf(entry, entry, entry, entry, entry)

    val myEntriesToShow: List<Event> = emptyList()
    var entriesToShow by rememberSaveable { mutableStateOf(myEntriesToShow) }

    //Note that selectedDay cant utilize 'rememberSavable' because it'll crash when rotating
    //the screen.  --TODO fix this problem?
    var selectedDay: LocalDate?

    Column {
        Kalendar(
            onCurrentDayClick = { kDay, kEvents ->
                println("${kDay.localDate} has the following events: $kEvents")
                selectedDay = kDay.localDate.toJavaLocalDate()
                entriesToShow = entries.filter { event -> event.start.toLocalDate() == selectedDay }
            }, kalendarType = KalendarType.Firey, kalendarEvents = emptyList()

        )
        if (entriesToShow.isNotEmpty()) {
            Text(
                text = "Scheduled Events",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
            )
            LazyColumn(
                contentPadding = PaddingValues(bottom = 64.dp)
            ) { items(entriesToShow) { TaskCard(it, nav) } }
        }
    }
}