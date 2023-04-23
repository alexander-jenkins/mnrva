package edu.towson.cosc435.mnrva.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.authentication.LoginView
import edu.towson.cosc435.mnrva.ui.authentication.RegisterView
import edu.towson.cosc435.mnrva.ui.calenderView.Calendar
import edu.towson.cosc435.mnrva.ui.home.HomeView
import edu.towson.cosc435.mnrva.ui.newEntry.NewEntryView
import edu.towson.cosc435.mnrva.ui.schedule.ScheduleView
import edu.towson.cosc435.mnrva.ui.settings.SettingsView

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun MnrvaNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val authViewModel = AuthenticationViewModel()

    NavHost(
        navController = navController, startDestination = Routes.RegisterView.route
    ) {

        // Login
        composable(Routes.LoginView.route) { LoginView(authViewModel) }

        // Register
        composable(Routes.RegisterView.route) { RegisterView(authViewModel) }

        // Home
        composable(Routes.HomeView.route) { HomeView() }

        // Schedule
        composable(Routes.ScheduleView.route) {
            ScheduleView(onTaskPress = { navController.navigate(Routes.NewEntryView.route) })
        }

        // New Item
        composable(Routes.NewEntryView.route) { Text("New Item") }

        // Calendar
        composable(Routes.CalendarView.route) {
            Calendar()
        }

        // Settings
        composable(Routes.SettingsView.route) { SettingsView() }

        //NewEntry
        composable(Routes.NewEntryView.route) { NewEntryView() }

    }
}