package edu.towson.cosc435.mnrva.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.mnrva.R

@Composable
fun SettingsView(vm: SettingsViewModel = viewModel()) {
    // images
    val userSettings = painterResource(R.drawable.user_settings)
    val notificationSettings = painterResource(R.drawable.notification_settings)
    val systemSettings = painterResource(R.drawable.system_settings)

    Column {

        // Title
        Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 16.dp)) {
            Text(
                stringResource(R.string.settingsHeader),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Account Settings
        Button(vm::logout, modifier = Modifier.padding(horizontal = 8.dp), content = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = userSettings,
                    null,
                    Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        stringResource(R.string.account),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(stringResource(R.string.accountSettingsDesc))
                }
            }
        })

        Spacer(Modifier.size(12.dp))

        // Notification Settings
        Button(onClick = {}, modifier = Modifier.padding(horizontal = 8.dp), content = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = notificationSettings,
                    null,
                    Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        stringResource(R.string.notifications),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(stringResource(R.string.notificationSettingsDesc))
                }
            }
        })

        Spacer(Modifier.size(12.dp))

        // System Settings
        Button(onClick = {}, modifier = Modifier.padding(horizontal = 8.dp), content = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = systemSettings,
                    null,
                    Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        stringResource(R.string.system),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(stringResource(R.string.systemSettingsDesc))
                }
            }
        })
    }
}