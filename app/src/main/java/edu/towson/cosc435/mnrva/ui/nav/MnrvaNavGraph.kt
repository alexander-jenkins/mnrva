package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.calenderView.Calendar
import edu.towson.cosc435.mnrva.ui.home.HomeView
import edu.towson.cosc435.mnrva.ui.newEntry.NewEntryView
import edu.towson.cosc435.mnrva.ui.schedule.ScheduleView
import edu.towson.cosc435.mnrva.ui.settings.SettingsView

@ExperimentalFoundationApi
@Composable
fun MnrvaNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HomeView.route) {

        // Schedule
        composable(Routes.ScheduleView.route) { ScheduleView(navController) }

        // New Entry
        composable(Routes.NewEntryView.route) { NewEntryView() }

        // Calendar
        composable(Routes.CalendarView.route) { Calendar(navController) }

        // Settings
        composable(Routes.SettingsView.route) { SettingsView() }

        // Home
        composable(Routes.HomeView.route) { HomeView(navController) }
    }
}