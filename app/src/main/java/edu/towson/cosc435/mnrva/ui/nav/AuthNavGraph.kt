package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.authentication.LoginView
import edu.towson.cosc435.mnrva.ui.authentication.RegisterView

@Composable
fun AuthNavGraph(nav: NavHostController = rememberNavController(), authViewModel: AuthenticationViewModel) {
    NavHost(nav, Routes.LoginView.route) {
        composable(Routes.LoginView.route) { LoginView(authViewModel) }
        composable(Routes.RegisterView.route) { RegisterView(authViewModel) }
    }
}