package edu.towson.cosc435.mnrva.ui

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.ui.nav.NavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
     NavGraph(nav)
}
}

@Composable
private fun BottomBar(
    nav: NavHostController
) {
    BottomNavigation(
        elevation = 16.dp
    ) {
        val backStack by nav.currentBackStackEntryAsState()
        val destination = backStack?.destination

        BottomNavigationItem()

        BottomNavigationItem()

        BottomNavigationItem()

        BottomNavigationItem()

        BottomNavigationItem()
    }
}