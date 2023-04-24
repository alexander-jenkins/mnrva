package edu.towson.cosc435.mnrva.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.nav.AuthNavGraph
import edu.towson.cosc435.mnrva.ui.nav.Routes

@Composable
fun AuthScreen(vm: AuthenticationViewModel) {
    val authNav = rememberNavController()

    Scaffold( bottomBar = { AuthBottomBar(authNav) }) {
        Log.d("Padding values:", "$it")
        AuthNavGraph(authNav, vm)
    }
}

@Composable
fun AuthBottomBar(nav: NavHostController) {
    BottomNavigation {
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
