package edu.towson.cosc435.mnrva.ui.EditorDialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun EditStartTimeDialog(eventEditorVM: EditorDialogViewModel) {
    val time by eventEditorVM.start

    val timePicker = TimePickerDialog(
        LocalContext.current, eventEditorVM::setStart, time.hour, time.minute, false
    )

    val datePicker = DatePickerDialog(
        LocalContext.current, { picker: DatePicker, year: Int, month: Int, day: Int ->
            run {
                eventEditorVM.setStart(
                    picker, year, month, day
                )
                timePicker.show()
            }
        }, time.year, (time.monthValue - 1), time.dayOfMonth
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Start:", fontSize = 18.sp)
        Button(datePicker::show) {
            Text(text = eventEditorVM.start.value.format(eventEditorVM.startFormatter))
        }
    }
}