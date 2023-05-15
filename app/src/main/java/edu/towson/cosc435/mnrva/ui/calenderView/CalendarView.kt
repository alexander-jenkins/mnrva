package edu.towson.cosc435.mnrva.ui.calenderView

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.data.model.event.Event
import edu.towson.cosc435.mnrva.ui.EntryList.EntryList
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.editorDialog.EditorDialogViewModel
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDate

@Composable
fun Calendar(eventVM: EventViewModel, eventEditorVM: EditorDialogViewModel) {
    val orientation = LocalConfiguration.current.orientation

    val entries = eventVM.allEvents
    var entriesToShow: List<Event> by remember { mutableStateOf(emptyList()) }
    var selectedDay by remember { mutableStateOf(LocalDate.now()) }
    entriesToShow = entries.filter { event ->
        event.start.toLocalDate().toKotlinLocalDate() == selectedDay.toKotlinLocalDate()
    }

    if (orientation == Configuration.ORIENTATION_PORTRAIT) Column {
        Kalendar(
            onCurrentDayClick = { kDay, kEvents ->
                selectedDay = kDay.localDate.toJavaLocalDate()
                entriesToShow = entries.filter { event ->
                    event.start.toLocalDate().toKotlinLocalDate() == selectedDay.toKotlinLocalDate()
                }
            },
            kalendarType = KalendarType.Firey,
            kalendarEvents = emptyList(),
            takeMeToDate = selectedDay.toKotlinLocalDate()
        )
        EntryList(entriesToShow, selectedDay, eventEditorVM::setSelected)
    }
    else if ((orientation == Configuration.ORIENTATION_LANDSCAPE) || (orientation == Configuration.ORIENTATION_UNDEFINED)) Row {
        Row(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight()
        ) {
            Kalendar(
                onCurrentDayClick = { kDay, kEvents ->
                    selectedDay = kDay.localDate.toJavaLocalDate()
                    entriesToShow = entries.filter { event ->
                        event.start.toLocalDate().toKotlinLocalDate() == selectedDay.toKotlinLocalDate()
                    }
                },
                kalendarType = KalendarType.Firey,
                kalendarEvents = emptyList(),
                takeMeToDate = selectedDay.toKotlinLocalDate()
            )
        }
        Row {
            EntryList(entriesToShow, selectedDay, eventEditorVM::setSelected)

        }
    }
}