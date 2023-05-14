package edu.towson.cosc435.mnrva.ui.taskCard

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
import edu.towson.cosc435.mnrva.ui.EditDialogBox
import edu.towson.cosc435.mnrva.ui.theme.Envy
import edu.towson.cosc435.mnrva.ui.theme.FringyFlower
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(event: Event) {
    val horizontalGradientBrush = Brush.horizontalGradient(colors = listOf(FringyFlower, Envy))
    val formatter = DateTimeFormatter.ofPattern("EEEE 'at' h:mm a")
    var showDialog by remember { mutableStateOf(false) }

    EditDialogBox(event, showDialog, onDialogueExit = { showDialog = false })

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 16.dp,
        onClick = { showDialog = true },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
    ) {
        Box(modifier = Modifier
            .background(brush = horizontalGradientBrush)
            .padding(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) { Text(event.title, fontSize = 36.sp) }
                    if (event.description != null && event.description != "") Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            event.description,
                            fontSize = 18.sp,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) { Text(event.start.format(formatter)) }
                    if (event.tags != null && event.tags != "") Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        event.tags.split(" ").map {
                            Card(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .background(Color(25))
                                    .padding(4.dp)
                            ) { Text(it) }
                        }
                    }


                }
            }
        }
    }
}