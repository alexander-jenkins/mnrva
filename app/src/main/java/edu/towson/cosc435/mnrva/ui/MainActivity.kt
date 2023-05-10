package edu.towson.cosc435.mnrva.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.ui.theme.MNRVATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MNRVATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            DependencyGraph.authenticated.value =
                                !DependencyGraph.authenticated.value
                        }) { Text(text = "Flip Auth") }

                        MnrvaApp()


                    }

                }
            }
        }
    }
}