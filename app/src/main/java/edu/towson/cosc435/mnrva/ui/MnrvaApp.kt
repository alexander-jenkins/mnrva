package edu.towson.cosc435.mnrva.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.ui.authentication.AuthScreen
import edu.towson.cosc435.mnrva.ui.home.Home


@Composable
fun MnrvaApp() {

    Scaffold() {
        Surface(modifier = Modifier.padding(it)) {

            // Is the user authenticated already?
            if (DependencyGraph.authenticated.value) Home()
            else AuthScreen()

        }
    }

}