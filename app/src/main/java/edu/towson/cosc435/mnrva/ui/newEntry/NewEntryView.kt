package edu.towson.cosc435.mnrva.ui.newEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.home.TaskCard
import edu.towson.cosc435.mnrva.ui.nav.Routes
import edu.towson.cosc435.mnrva.ui.theme.Envy
import edu.towson.cosc435.mnrva.ui.theme.FringyFlower
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun NewEntryView () {
    val title : MutableState<String> = remember { mutableStateOf("") }
    val date : MutableState<String> = remember { mutableStateOf("") }
    val time : MutableState<String> = remember { mutableStateOf("") }

    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = title.value,
                onValueChange = { newValue: String ->
                    title.value = newValue
                },
                label = { Text("Enter Title:") }
            )

            OutlinedTextField(
                value = date.value,
                onValueChange = { newValue: String ->
                    date.value = newValue
                },
                label = { Text("Enter Date:") }
            )

            OutlinedTextField(
                value = time.value,
                onValueChange = { newValue: String ->
                    time.value = newValue
                },
                label = { Text("Enter Time:") }
            )

            Button(
                onClick = {},
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
            ) {
                // Inner content including an icon and a text label
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Create")
            }
        }
    }

}

