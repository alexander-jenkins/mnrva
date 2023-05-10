package edu.towson.cosc435.mnrva.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.ui.authentication.AuthScreen
import edu.towson.cosc435.mnrva.ui.home.Home

@Composable
fun MnrvaApp() {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            if (DependencyGraph.settingsRepository.authenticated.value) Home()
            else AuthScreen()
        }
    }

}