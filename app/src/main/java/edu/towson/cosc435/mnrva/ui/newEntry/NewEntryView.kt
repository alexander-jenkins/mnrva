package edu.towson.cosc435.mnrva.ui.newEntry

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
fun NewEntryView(vm: NewEntryViewModel = viewModel()) {
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

        StartDateTimePicker()
        EndDateTimePicker()


        //set description
        OutlinedTextField(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
            value = vm.description.value,
            onValueChange = vm::setDescription,
            label = { Text("Description") })

        // tags
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            value = vm.tags.value,
            onValueChange = vm::setTags,
            singleLine = true,
            label = { Text("Tags (space separated)") })

        //create button
        Button(
            modifier = Modifier.width(130.dp),
            onClick = vm::createEvent,
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


