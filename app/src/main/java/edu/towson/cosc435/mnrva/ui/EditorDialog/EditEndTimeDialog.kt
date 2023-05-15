package edu.towson.cosc435.mnrva.ui.EditorDialog

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
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDateTime

@Composable
fun EditEndTimeDialog(vm: EditorDialogViewModel = viewModel()) {
    val time = vm.end.value
    val timePicker = TimePickerDialog(
        LocalContext.current,
        vm::setEnd,
        time?.hour ?: LocalDateTime.now().hour,
        time?.minute ?: LocalDateTime.now().minute,
        false
    )


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Start:", fontSize = 18.sp)
        Button(timePicker::show) {
            Text(text = "${time?.format(vm.formatter) ?: ""}")
        }
    }

}