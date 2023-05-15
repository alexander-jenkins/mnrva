package edu.towson.cosc435.mnrva.ui.event.creator

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewEntryView(newEntryVM: NewEntryViewModel = viewModel()) {
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
            value = newEntryVM.title.value,
            onValueChange = newEntryVM::setTitle,
            label = { Text("Title") },
            singleLine = true
        )

        StartDateTimePicker(newEntryVM)
        EndDateTimePicker(newEntryVM)


        //set description
        OutlinedTextField(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
            value = newEntryVM.description.value,
            onValueChange = newEntryVM::setDescription,
            label = { Text("Description") })

        // tags
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            value = newEntryVM.tags.value,
            onValueChange = newEntryVM::setTags,
            singleLine = true,
            label = { Text("Tags (space separated)") })

        //create button
        Button(
            modifier = Modifier.width(130.dp),
            onClick = newEntryVM::createEvent,
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

//private fun showNotification(ctx: Context) {
//    val notificationManager = MnrvaApplication.notificationManager
//    val notification =
//        NotificationCompat.Builder(ctx, MnrvaApplication.channel_id).setContentText("Hello text")
//            .setContentTitle("hello title").setSmallIcon(R.drawable.notification_settings).build()
//    notificationManager.notify(1, notification)
//}