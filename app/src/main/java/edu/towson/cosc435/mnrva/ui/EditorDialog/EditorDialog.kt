package edu.towson.cosc435.mnrva.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.mnrva.model.Event
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditorDialogViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EditDialogBox(entry: Event, showDialog: Boolean, onDialogueExit: () -> Unit, vm: EditorDialogViewModel = viewModel()){

    var tempTitle by remember { mutableStateOf(entry.title) }
    var tempDate by remember { mutableStateOf(entry.start) }
    var tempStartTime by remember { mutableStateOf(entry.start) }
    var tempEndTime by remember { mutableStateOf(entry.end) }
    var tempDescription by remember { mutableStateOf(entry.description) }
    var tempTag by remember { mutableStateOf(entry.tags) }

    val scrollState = rememberScrollState()

    val format = DateTimeFormatter.ofPattern("yyyy/M/d")
    val timeFormat = DateTimeFormatter.ofPattern("h:mm a")

    //todo -- optional -- make it so you cant set endTime before startTime and vice-versa
    //declares date picker
    val mContext = LocalContext.current
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            tempDate =  LocalDate.parse("$year/${month+1}/$day", format).atStartOfDay()
        }, entry.start.year, entry.start.monthValue + 1, entry.start.dayOfMonth
    )
    //declares start time picker
    val startTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            tempStartTime = LocalDateTime.of(tempDate.year,tempDate.month,tempDate.dayOfMonth,mHour, mMinute)
        }, 6, 0, false
    )
    //declares End time picker
    val endTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            tempEndTime = LocalDateTime.of(tempDate.year,tempDate.month,tempDate.dayOfMonth,mHour, mMinute)
        }, 6, 0, false
    )

    if (showDialog){
        Dialog(onDismissRequest = {onDialogueExit()},
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f)
                    .background(color = MaterialTheme.colors.background)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    //DATE PICKER
                    Button({
                        mDatePickerDialog.show()
                    }){
                        Text("Choose Date")
                    }
                    Text("${tempDate.dayOfWeek.toString().lowercase()}, ${tempDate.month.toString().lowercase()} ${tempDate.dayOfMonth}, ${tempDate.year}")
                    Spacer(modifier = Modifier.padding(all = 15.dp))

                    //START TIME PICKER
                    Button({
                        startTimePickerDialog.show()
                    }){
                        Text("Choose Start Time")
                    }
                    if (tempStartTime != null){
                        Text(tempStartTime!!.format(timeFormat))
                    }
                    else{
                        Text("No Start Time")
                    }

                    //END TIME PICKER
                    if (tempStartTime != null){
                        Spacer(modifier = Modifier.padding(all = 15.dp))
                        Button({
                            endTimePickerDialog.show()
                        }){
                            Text("Choose End Time")
                        }
                        if (tempEndTime != null){
                            Text(tempEndTime!!.format(timeFormat))
                        }
                        else{
                            Text("No End Time")
                        }
                    }
                    Spacer(modifier = Modifier.padding(all = 15.dp))


                    //Simple string editing
                    Text("Title")
                    TextField(value = tempTitle, onValueChange = {tempTitle = it})
                    Text("Description")
                    TextField(value = tempDescription.orEmpty(), onValueChange = {tempDescription = it})
                    Text("Tag")
                    TextField(value = tempTag.orEmpty(), onValueChange = {tempTag = it})

                    Spacer(modifier = Modifier.padding(all = 15.dp))


                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        //Confirmation Button
                        Button(onClick = {
                            onDialogueExit()
                            Log.d("MNRVA", "Edit event: ${entry.id}")

                            val theUpdate = Event(
                                id = entry.id,
                                owner = "USER",
                                title = tempTitle,
                                description = tempDescription,
                                start = tempStartTime,
                                end = tempEndTime,
                                tags = tempTag,
                            )
                            vm.updateEvent(theUpdate)

                            //todo Update entry in db here
                            //Should be a coroutine action as it is updating the database; this should also trigger
                            //refreshes of components that use Entries
                        }) {
                            Text(text = "Confirm")
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        //Delete Button
                        Button(
                            onClick = {
                                onDialogueExit()
                                Log.d("MNRVA", "Delete event: ${entry.id}")
                                vm.deleteEvent(entry)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Red
                            )
                        ) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
        }
    }
}