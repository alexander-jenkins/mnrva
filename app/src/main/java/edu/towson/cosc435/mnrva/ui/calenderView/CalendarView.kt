package edu.towson.cosc435.mnrva.ui.calenderView

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.model.Entry
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(nav: NavHostController) {

    val entry = Entry(
        id = 0,
        title = "Job Interview",
        date = LocalDateTime.of(2023, 4, 26, 23, 1),
        startTime = null,
        endTime = null,
        description = "interview with the best company ever",
        tag = "my tag"
    )

    val entries = listOf(entry, entry, entry, entry, entry)

    val myEntriesToShow: List<Entry> = emptyList()
    var entriesToShow by rememberSaveable { mutableStateOf(myEntriesToShow) }

    //Note that selectedDay cant utilize 'rememberSavable' because it'll crash when rotating
    //the screen.  --TODO fix this problem?
    var selectedDay: LocalDate?

    Column {
        Kalendar(
            onCurrentDayClick = { kDay, kEvents ->
                println("${kDay.localDate} has the following events: $kEvents")
                selectedDay = kDay.localDate.toJavaLocalDate()
                entriesToShow = entries.filter { event -> event.date.toLocalDate() == selectedDay }
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
