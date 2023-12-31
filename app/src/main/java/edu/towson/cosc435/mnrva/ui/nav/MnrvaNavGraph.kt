package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.ui.HomeView
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.authentication.LoginView
import edu.towson.cosc435.mnrva.ui.authentication.RegisterView
import edu.towson.cosc435.mnrva.ui.event.Calendar
import edu.towson.cosc435.mnrva.ui.event.EventViewModel
import edu.towson.cosc435.mnrva.ui.event.ScheduleView
import edu.towson.cosc435.mnrva.ui.event.creator.NewEntryView
import edu.towson.cosc435.mnrva.ui.event.editor.EditDialogBox
import edu.towson.cosc435.mnrva.ui.event.editor.EditorDialogViewModel
import edu.towson.cosc435.mnrva.ui.settings.SettingsView

@Composable
fun MnrvaNavGraph(navController: NavHostController = rememberNavController()) {
    val authenticated by DependencyGraph.settingsRepository.authenticated

    Scaffold(bottomBar = {
        if (authenticated) AuthenticatedBottomBar(nav = navController)
        else UnauthenticatedBottomBar(nav = navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            if (authenticated) {
                val eventVM: EventViewModel = viewModel()
                val editorVM: EditorDialogViewModel = viewModel()
                EditDialogBox(editorVM)
                NavHost(navController = navController, startDestination = Routes.HomeView.route) {
                    composable(Routes.ScheduleView.route) { ScheduleView(eventVM, editorVM) }
                    composable(Routes.NewEntryView.route) { NewEntryView() }
                    composable(Routes.CalendarView.route) { Calendar(eventVM, editorVM) }
                    composable(Routes.SettingsView.route) { SettingsView() }
                    composable(Routes.HomeView.route) { HomeView(eventVM, editorVM) }
                }
            } else {
                val authViewModel: AuthenticationViewModel = viewModel()
                NavHost(navController = navController, Routes.LoginView.route) {
                    composable(Routes.LoginView.route) { LoginView() }
                    composable(Routes.RegisterView.route) { RegisterView(authViewModel) }
                }
            }
        }
    }
}