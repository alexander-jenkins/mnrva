package edu.towson.cosc435.mnrva.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.R
import edu.towson.cosc435.mnrva.ui.nav.MnrvaNavGraph
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val nav = rememberNavController()

    // Scaffolding for the app
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(nav) }
    ) {
        MnrvaNavGraph(nav)
    }

}

@Composable
private fun TopBar() {
    Text(stringResource(R.string.app_name))
}

@Composable
private fun BottomBar(
    navHost: NavHostController,
) {
    BottomNavigation(elevation = 16.dp) {

        // Home Button
        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Default.Home, "") },
            label = { Text("Home") },
            onClick = {}
        )

        // Schedule Button
        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Default.List, "") },
            label = { Text("Schedule") },
            onClick = {}
        )

        // Add Item Button
        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Default.Add, "") },
            label = { Text("New") },
            onClick = {}
        )

        // Calendar Button
        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Default.DateRange, "") },
            label = { Text("Calendar") },
            onClick = {}
        )

        // Settings Button
        BottomNavigationItem(
            selected = false,
            icon = { Icon(Icons.Default.Settings, "") },
            label = { Text("Settings") },
            onClick = {}
        )


    }
}