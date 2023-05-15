package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.EventViewModel
import edu.towson.cosc435.mnrva.ui.calenderView.Calendar
import edu.towson.cosc435.mnrva.ui.editorDialog.EditorDialogViewModel
import edu.towson.cosc435.mnrva.ui.home.HomeView
import edu.towson.cosc435.mnrva.ui.newEntry.NewEntryView
import edu.towson.cosc435.mnrva.ui.schedule.ScheduleView
import edu.towson.cosc435.mnrva.ui.settings.SettingsView

@ExperimentalFoundationApi
@Composable
fun MnrvaNavGraph(navController: NavHostController = rememberNavController()) {
    val eventVM: EventViewModel = viewModel()
    val editorVM: EditorDialogViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.HomeView.route) {
        composable(Routes.ScheduleView.route) { ScheduleView(eventVM, editorVM) }
        composable(Routes.NewEntryView.route) { NewEntryView() }
        composable(Routes.CalendarView.route) { Calendar(eventVM, editorVM) }
        composable(Routes.SettingsView.route) { SettingsView() }
        composable(Routes.HomeView.route) { HomeView(eventVM, editorVM) }
    }
}