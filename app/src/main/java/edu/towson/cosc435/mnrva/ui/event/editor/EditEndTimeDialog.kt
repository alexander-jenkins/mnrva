package edu.towson.cosc435.mnrva.ui.event.editor

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun EditEndTimeDialog(eventEditorVM: EditorDialogViewModel) {
    val time = eventEditorVM.end.value
    val timePicker = TimePickerDialog(
        LocalContext.current,
        eventEditorVM::setEnd,
        time?.hour ?: LocalDateTime.now().hour,
        time?.minute ?: LocalDateTime.now().minute,
        false
    )


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "  End:", fontSize = 18.sp)
        Button(timePicker::show) {
            Text(text = "${time?.format(eventEditorVM.endFormatter) ?: ""}")
        }
    }

}