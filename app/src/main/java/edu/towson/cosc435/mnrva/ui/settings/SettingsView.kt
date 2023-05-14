package edu.towson.cosc435.mnrva.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsView(vm: SettingsViewModel = viewModel()) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // title for the settings view
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Settings", fontSize = 32.sp)
            }
            Divider()

            // manually trigger an event sync with the server
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Synchronize with database:", modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = vm::downloadEvents, content = { Text(text = "Sync") })
            }
            Divider()

            // Toggle switch for the notifications setting
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Enable notifications?", modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    Switch(
                        checked = vm.useNotifications.value,
                        onCheckedChange = vm::setUseNotifications
                    )
                }
            }
            Divider()
        }

        // logout button - clears the user's token
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = vm::logout, content = { Text(text = "Logout") })
        }

    }
}


// TODO logout btn -- logout from api

// TODO sync button -- push/pull from mongo

// TODO Change password

// TODO notifications -- on/off