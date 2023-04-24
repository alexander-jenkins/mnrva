package edu.towson.cosc435.mnrva.ui.newEntry

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun NewEntryView () {
    val title: MutableState<String> = remember { mutableStateOf("") }
    val date: MutableState<String> = remember { mutableStateOf("") }
    val startTime: MutableState<String> = remember { mutableStateOf("") }
    val endTime: MutableState<String> = remember { mutableStateOf("") }
    val description: MutableState<String> = remember { mutableStateOf("") }
    val showStartTime: MutableState<Boolean> = remember { mutableStateOf(false) }
    val showEndTime: MutableState<Boolean> = remember { mutableStateOf(false) }


    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()

    // finds current time/date
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    mCalendar.time = Date()

    //declares date picker
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    //declares start time picker
    val sTimePickerDialog = TimePickerDialog(
            mContext,
    {_, mHour : Int, mMinute: Int ->
        startTime.value = "$mHour:$mMinute"
    }, mHour, mMinute, false
    )

    //declares end time picker
    val eTimePickerDialog = TimePickerDialog(
            mContext,
    {_, mHour : Int, mMinute: Int ->
        endTime.value = "$mHour:$mMinute"
    }, mHour, mMinute, false
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(50.dp),
            verticalArrangement = spacedBy(10.dp),
        )
        {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Make New Entry:",
                    fontSize = 36.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
            //Entry Title Field*
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title.value,
                    onValueChange = { newValue: String ->
                        title.value = newValue
                    },
                    label = { Text("Title") }
                )
            }

            //Entry date Field*
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(130.dp),
                    onClick = {mDatePickerDialog.show()},

                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) {
                    Text(
                        "Select Date",
                        fontSize = 15.sp
                    )
                }

                Text(text = "    Date: ${date.value}", fontSize = 15.sp)
            }

            //Checkboxes for showing start and end time
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = showStartTime.value,
                    onCheckedChange = {
                        showStartTime.value = it


                        if (!showStartTime.value && showEndTime.value) {
                            showEndTime.value = it
                        }
                    }
                )
                Text(text = "Set Start Time")

                if (showStartTime.value) {
                    Checkbox(
                        checked = showEndTime.value,
                        onCheckedChange = {
                            showEndTime.value = it

                        }
                    )
                    Text(text = "Set End Time")
                }

            }

            //set start time
            if (showStartTime.value) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {sTimePickerDialog.show()},

                        contentPadding = PaddingValues(
                            top = 12.dp,
                            bottom = 12.dp
                        )
                    ) {
                        Text(
                            "Select Start",
                            fontSize = 15.sp
                        )
                    }

                    Text(text = "    Start: ${startTime.value}", fontSize = 15.sp)
                }
            }

            //set end time
            if (showStartTime.value && showEndTime.value) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {eTimePickerDialog.show()},

                        contentPadding = PaddingValues(
                            top = 12.dp,
                            bottom = 12.dp
                        )
                    ) {
                        Text(
                            "Select End",
                            fontSize = 15.sp
                        )
                    }

                    Text(text = "    End Time: ${endTime.value}", fontSize = 15.sp)
                }
            }


            //set description
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier.height(200.dp)
                        .fillMaxWidth(),
                    value = description.value,
                    onValueChange = { newValue: String ->
                        description.value = newValue
                    },
                    label = { Text("Description") }
                )
            }


            //create button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(130.dp),
                    onClick = {},

                    contentPadding = PaddingValues(
                        top = 12.dp,
                        bottom = 12.dp
                    )
                ) {
                    Text(
                        "Create",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}


