package edu.towson.cosc435.mnrva.ui.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun HomeView() {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                "Hello Friend!",
                fontSize = 36.sp,
                modifier = Modifier.padding(5.dp)
            )

            Text(
                "Here are your upcoming plans:",
                fontSize = 15.sp,
            )

            Spacer(modifier = Modifier.padding(50.dp))

            Text(
                "This week:",
                fontSize = 15.sp,
            )

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 16.dp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
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
                            Text("Job Interview", fontSize = 36.sp,
                                modifier = Modifier.weight(1.0f))
                        }
                        Row(
                            modifier = Modifier.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Artist:", modifier = Modifier.weight(1.0f))
                        }

                    }
                }
            }
        }
    }
}