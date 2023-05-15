package edu.towson.cosc435.mnrva.ui.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UnauthenticatedBottomBar(nav: NavHostController) =
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