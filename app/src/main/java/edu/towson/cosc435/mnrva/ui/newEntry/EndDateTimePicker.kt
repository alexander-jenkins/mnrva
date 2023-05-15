package edu.towson.cosc435.mnrva.ui.newEntry

import android.app.TimePickerDialog
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
fun EndDateTimePicker(vm: NewEntryViewModel) {
    val time by vm.end
    val timePicker = TimePickerDialog(
        LocalContext.current, vm::setEnd, time.hour, time.minute, false
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "  End:", fontSize = 18.sp)
        Button(timePicker::show) {
            Text(text = vm.end.value.format(vm.endFormat))
        }
    }
}