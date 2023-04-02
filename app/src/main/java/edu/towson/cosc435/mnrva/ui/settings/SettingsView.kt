package edu.towson.cosc435.mnrva.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.mnrva.R

@Composable
fun SettingsView() {
    Column(modifier = Modifier.padding(12.dp)) {

        // Title
        Row {
            Text(
                stringResource(R.string.settingsHeader), fontSize = 32.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }


        // Account Settings
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Column {
                Text(stringResource(R.string.account), fontSize = 24.sp) // Title
                Text(stringResource(R.string.accountSettingsDesc)) // Desc
            }
        }

        // Notification Settings
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Column {
                Text(stringResource(R.string.notifications), fontSize = 24.sp) // Title
                Text(stringResource(R.string.notificationSettingsDesc)) // Desc
            }
        }

        // System Settings
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Column {
                Text(stringResource(R.string.system), fontSize = 24.sp) // Title
                Text(stringResource(R.string.systemSettingsDesc)) // Desc
            }
        }

    }
}
