package edu.towson.cosc435.mnrva.ui.calenderView

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.model.Entry
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(){

    val entry = Entry(
        id=0,
        title = "my title",
        date = LocalDateTime(2023,4,4,23,1).toString(),
        start_time = "",
        end_time = "",
        description = "my Description",
        tag = "my tag"
    )

    val entries = listOf(entry, entry, entry, entry, entry)

    val myEntriesToShow: List<Entry> = emptyList()
    var entriesToShow by rememberSaveable { mutableStateOf(myEntriesToShow) }

    //Note that selectedDay cant utilize 'rememberSaveable' because it'll crash when rotating
    //the screen.  --TODO fix this problem?
    var selectedDay: LocalDate?

    Column {
        Kalendar(
            onCurrentDayClick={kDay,kEvents->
                println("${kDay.localDate} has the following events: $kEvents")
                selectedDay = kDay.localDate
                entriesToShow = entries.filter { event -> LocalDateTime.parse(event.date).date == selectedDay }
            },
            kalendarType=KalendarType.Firey,
            kalendarEvents = emptyList()

        )
        if (entriesToShow.isNotEmpty()){
            Text(text = "Scheduled Events")
            LazyColumn{
                items(entriesToShow){
                        event ->
                    TaskCard(taskName = event.title, dateTime = java.time.LocalDateTime.parse(event.date), tag = event.tag)
                }
            }
        }
    }
}
