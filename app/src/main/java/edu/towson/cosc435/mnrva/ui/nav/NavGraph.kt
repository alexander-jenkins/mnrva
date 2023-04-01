package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
)
{
    NavHost(
        navController = navController,
        startDestination = Routes.HomeView.route
    ) {

        composable(Routes.HomeView.route) {

        }

        composable(Routes.ScheduleView.route) {

        }

        composable(Routes.CalendarView.route) {

        }

        composable(Routes.SettingsView.route) {

        }

        composable(Routes.EntryView.route) {

        }

        composable(Routes.NewEntryView.route) {

        }

    }
}