package edu.towson.cosc435.mnrva.ui.calenderView

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.model.Event
import edu.towson.cosc435.mnrva.ui.EntryList.EntryList
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDateTime

@Composable
fun Calendar(nav: NavHostController, vm: CalendarViewModel = viewModel()) {

    val event = Event(
        id = "0",
        owner = "me",
        title = "Job Interview",
        start = LocalDateTime.of(2023, 4, 26, 23, 1),
        end = null,
        description = "interview with the best company ever",
        tags = "my tag"
    )

    val entries = listOf(event, event, event, event, event)

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        //PORTRAIT MODE
        Configuration.ORIENTATION_PORTRAIT -> {
            Column {
                Kalendar(
                    onCurrentDayClick = { kDay, kEvents ->
                        vm.selectedDay = kDay.localDate.toJavaLocalDate()
                        vm.entriesToShow =
                            vm.entries.filter { event -> event.start.toLocalDate() == vm.selectedDay }
                        println("${kDay.localDate} has the following events: $vm.entriesToShow")
                    },
                    kalendarType = KalendarType.Firey,
                    kalendarEvents = emptyList(),
                    takeMeToDate = vm.selectedDay?.toKotlinLocalDate()
                )
                EntryList(vm.entriesToShow, vm.selectedDay)
            }
        }

        else -> {//LANDSCAPE MODE
            BoxWithConstraints {
                val boxWithConstraintsScope = this
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Kalendar(
                        modifier = Modifier
                            .width(boxWithConstraintsScope.maxWidth / 2)
                            .height(boxWithConstraintsScope.maxHeight * 0.9f),
                        onCurrentDayClick = { kDay, kEvents ->

                            vm.selectedDay = kDay.localDate.toJavaLocalDate()
                            vm.entriesToShow =
                                vm.entries.filter { event -> event.start.toLocalDate() == vm.selectedDay }
                            println("${kDay.localDate} has the following events: $vm.entriesToShow")
                        },
                        kalendarType = KalendarType.Firey,
                        kalendarEvents = emptyList(),
                        takeMeToDate = vm.selectedDay?.toKotlinLocalDate()
                    )
                    EntryList(vm.entriesToShow, vm.selectedDay)
                }
            }
        }
    }
}