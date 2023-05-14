package edu.towson.cosc435.mnrva.ui.newEntry

import android.Manifest
import android.app.*
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
<<<<<<< 20e42f4c3d301c877d99b32903e37445e673ec8e
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
=======
import edu.towson.cosc435.mnrva.MnrvaApplication
import edu.towson.cosc435.mnrva.R
import edu.towson.cosc435.mnrva.ui.MainActivity
>>>>>>> Setup some basic functions for notifications
import java.util.Calendar
import java.util.Date

@Composable
fun NewEntryView(vm: NewEntryViewModel = viewModel()) {
    val date: MutableState<LocalDate> = remember { mutableStateOf(LocalDate.now()) }
    val endTime: MutableState<String> = remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()

    val format = DateTimeFormatter.ofPattern("yyyy/M/d")
    val timeFormat = DateTimeFormatter.ofPattern("h:mm a")

    var tempDate = LocalDate.now();



    // finds current time/date
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val context = LocalContext.current

    mCalendar.time = Date()

    //declares date picker
    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, year: Int, month: Int, day: Int ->
            tempDate = LocalDate.parse("$year/${month+1}/$day", format)
        }, mYear, mMonth, mDay
    )

    //declares start time picker
    val sTimePickerDialog = TimePickerDialog(
        mContext, { _, mHour: Int, mMinute: Int ->
            vm.setStart(LocalDateTime.of(tempDate.year,tempDate.month,tempDate.dayOfMonth,mHour, mMinute))
        }, mHour, mMinute, false
    )

    //declares end time picker
    val eTimePickerDialog = TimePickerDialog(
        mContext, { _, mHour: Int, mMinute: Int ->
            vm.setEnd(LocalDateTime.of(tempDate.year,tempDate.month,tempDate.dayOfMonth,mHour, mMinute))
            //endTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    Column(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize(),
        verticalArrangement = spacedBy(10.dp),
    ) {
        Text(
            "Make New Entry:", fontSize = 36.sp, modifier = Modifier.padding(5.dp)
        )

        //Entry Title Field*
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = vm.title.value,
            onValueChange = vm::setTitle,
            label = { Text("Title") },
            singleLine = true
        )

        //Entry date Field*
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.width(130.dp),
                onClick = mDatePickerDialog::show,
                contentPadding = PaddingValues(
                    start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp
                )
            ) {
                Text(
                    "Select Date", fontSize = 15.sp
                )
            }

            Text(text = "    Date: ${date.value}", fontSize = 15.sp)
        }

        //Checkboxes for showing start and end time
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = vm.showStart.value, onCheckedChange = {
                vm.showStart.value = it
                if (!vm.showStart.value && vm.showEnd.value) {
                    vm.showEnd.value = it
                }
            })
            Text(text = "Set Start Time")
            if (vm.showStart.value) {
                Checkbox(checked = vm.showEnd.value, onCheckedChange = vm::toggleShowEnd)
                Text(text = "Set End Time")
            }
        }

        //set start time
        if (vm.showStart.value) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(130.dp), onClick = { sTimePickerDialog.show() },

                    contentPadding = PaddingValues(
                        top = 12.dp, bottom = 12.dp
                    )
                ) {
                    Text(
                        "Select Start", fontSize = 15.sp
                    )
                }

                Text(text = "    Start: ${vm.start.value}", fontSize = 15.sp)
            }
        }

        //set end time
        if (vm.showStart.value && vm.showEnd.value) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(130.dp),
                    onClick = { eTimePickerDialog.show() },
                    contentPadding = PaddingValues(
                        top = 12.dp, bottom = 12.dp
                    )
                ) {
                    Text(
                        "Select End", fontSize = 15.sp
                    )
                }
                Text(text = "    End Time: ${vm.end.value}", fontSize = 15.sp)
            }
        }


        //set description
        OutlinedTextField(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
            value = vm.description.value,
            onValueChange = vm::setDescription,
            label = { Text("Description") })

        //create button
        Button(
            modifier = Modifier.width(130.dp),
            onClick = {
                vm.createEvent()
                showNotification(context)
                      },
            contentPadding = PaddingValues(
                top = 12.dp, bottom = 12.dp
            )
        ) {
            Text(
                "Create", fontSize = 15.sp
            )
        }

    }
}

private fun showNotification(ctx: Context){
    val notificationManager = MnrvaApplication.notificationManager
    val notification = NotificationCompat.Builder(ctx, MnrvaApplication.channel_id)
        .setContentText("Hello text")
        .setContentTitle("hello title")
        .setSmallIcon(R.drawable.notification_settings)
        .build()
    notificationManager.notify(1, notification)
}


