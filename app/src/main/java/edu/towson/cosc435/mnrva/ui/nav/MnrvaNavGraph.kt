package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalComposeApi::class)
@ExperimentalFoundationApi
@Composable
fun MnrvaNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeView.route
    ) {

        // Home
        composable(Routes.HomeView.route) {
            Text("Home Screen")
        }

        // Schedule
        composable(Routes.ScheduleView.route) {
            Text("Schedule")
        }

        // New Item
        composable(Routes.NewEntryView.route) {
            Text("New Item")
        }

        // Calendar
        composable(Routes.CalendarView.route) {
            Text("Calendar")
        }

        // Settings
        composable(Routes.SettingsView.route) {
            Text("Settings")
        }

    }
}