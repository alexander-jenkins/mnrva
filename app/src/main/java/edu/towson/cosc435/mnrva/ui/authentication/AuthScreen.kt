package edu.towson.cosc435.mnrva.ui.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.nav.AuthNavGraph
import edu.towson.cosc435.mnrva.ui.nav.Routes

@Composable
fun AuthScreen(vm: AuthenticationViewModel = viewModel()) {
    val authNav = rememberNavController()

    Scaffold(bottomBar = { AuthBottomBar(authNav) }) {
        Box(modifier = Modifier.padding(it)) {
            AuthNavGraph(authNav, vm)
        }
    }
}

@Composable
fun AuthBottomBar(nav: NavHostController) {
    BottomNavigation(backgroundColor = Color(0xFF2F3B52), elevation = 20.dp) {
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.AccountCircle, "") },
            label = { Text("Login") },
            onClick = {
                nav.navigate(Routes.LoginView.route) { }
            })
        BottomNavigationItem(selected = false,
            icon = { Icon(Icons.Default.AccountCircle, "") },
            label = { Text("Register") },
            onClick = {
                nav.navigate(Routes.RegisterView.route) { }
            })
    }
}
