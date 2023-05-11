package edu.towson.cosc435.mnrva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.mnrva.model.Event
import edu.towson.cosc435.mnrva.ui.theme.Envy
import edu.towson.cosc435.mnrva.ui.theme.FringyFlower
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(event: Event) {
    val horizontalGradientBrush = Brush.horizontalGradient(colors = listOf(FringyFlower, Envy))

    val formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm", Locale.ENGLISH)
    var showDialog by remember { mutableStateOf(false) }

    EditDialogBox(event, showDialog, onDialogueExit = {showDialog = false})

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 16.dp,
        onClick = {
            showDialog = true

        },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.background(brush = horizontalGradientBrush)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(modifier = Modifier.weight(1.5f)) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(event.title, fontSize = 36.sp, modifier = Modifier.weight(1.0f))
                    }
                    Row(
                        modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically
                    ) { Text(event.description.orEmpty(), fontSize = 18.sp, modifier = Modifier.weight(1.0f)) }

                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) { Text(event.start.format(formatter), modifier = Modifier.weight(1.0f)) }
                    Row(horizontalArrangement = Arrangement.End) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Card(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .background(Color(25))
                                .padding(bottom = 10.dp)
                        ) { Text(event.tags.orEmpty(), modifier = Modifier
                            .weight(1.0f)
                            .padding(all = 2.dp)) }
                    }
                }
            }
        }
    }
}