package edu.towson.cosc435.mnrva.ui.newEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewEntryView () {
    val date = LocalDate.of(2023, Month.APRIL, 2)
    val time = LocalTime.of(12, 30, 0)
    val dateTime = LocalDateTime.of(date, time)

    TaskCard(taskName = "Job Interview", dateTime = dateTime , tag = "WORK")
}

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskCard(
    taskName: String,
    dateTime: LocalDateTime,
    tag: String
) {
    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            FringyFlower,
            Envy
        )
    )
    val formatter = DateTimeFormatter.ofPattern("MMMM d, HH:mm", Locale.ENGLISH)
    val nav = rememberNavController()

    Card(

        shape = RoundedCornerShape(15.dp),
        elevation = 16.dp,
        onClick = { nav.navigate(Routes.NewEntryView.route) },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(brush = horizontalGradientBrush)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1.5f)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "$taskName", fontSize = 36.sp,
                            modifier = Modifier.weight(1.0f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.End
                    ) {
                        Text("${dateTime.format(formatter)}", modifier = Modifier.weight(1.0f))

                    }
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        Card(
                            modifier = Modifier.background(Color(25))
                        ) {
                            Text(tag.toString(), modifier = Modifier.weight(1.0f))
                        }
                    }

                }

            }
        }
    }
}