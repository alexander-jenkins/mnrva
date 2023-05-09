package edu.towson.cosc435.mnrva.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.theme.MNRVATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MNRVATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    if (DependencyGraph.settingsRepository.jwt.value != null) MainScreen()
                    else AuthScreen(AuthenticationViewModel {})

                }
            }
        }
    }
}