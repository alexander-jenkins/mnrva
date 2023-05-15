package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AuthenticatedBottomBar(nav: NavHostController) =
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