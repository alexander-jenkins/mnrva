package edu.towson.cosc435.mnrva.ui.calenderView

import androidx.compose.runtime.Composable
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.datetime.LocalDate


@Composable
fun Calendar(){
    val event1 = KalendarEvent(LocalDate(2023, 4,4), "", "")

    val kalenderEvents: List<KalendarEvent> = listOf(event1)

    Kalendar(
        onCurrentDayClick={kDay,kEvents->
            println("${kDay.localDate} has the following events: ${kEvents}")
    },
        kalendarType=KalendarType.Firey,
        kalendarEvents = kalenderEvents
    )
}

