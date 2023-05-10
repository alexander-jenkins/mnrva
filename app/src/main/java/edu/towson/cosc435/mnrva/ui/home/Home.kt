package edu.towson.cosc435.mnrva.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.nav.MnrvaNavGraph
import edu.towson.cosc435.mnrva.ui.nav.Routes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(nav: NavHostController = rememberNavController()) {
    // Scaffolding for the app
    Scaffold(bottomBar = { BottomBar(nav) }) {
        Box(Modifier.padding(it)) {
            MnrvaNavGraph(nav)
        }
    }
}

@Composable
private fun BottomBar(
    nav: NavHostController,
) {
    BottomNavigation(backgroundColor = Color(0xFF2F3B52), elevation = 20.dp) {
        // Home Button
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.Home, "") },
            label = { Text("Home") },
            onClick = {
                nav.navigate(Routes.HomeView.route) {
                    launchSingleTop = true
                    popUpTo(Routes.HomeView.route) { inclusive = false }
                }
            })

        // Schedule Button
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.List, "") },
            label = { Text("Schedule") },
            onClick = {
                nav.navigate(Routes.ScheduleView.route) { }
            })

        // Add Item Button
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.Add, "") },
            label = { Text("New") },
            onClick = { nav.navigate(Routes.NewEntryView.route) { } })

        // Calendar Button
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.DateRange, "") },
            label = { Text("Calendar") },
            onClick = { nav.navigate(Routes.CalendarView.route) })

        // Settings Button
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.Settings, "") },
            label = { Text("Settings") },
            onClick = { nav.navigate(Routes.SettingsView.route) })
    }
}