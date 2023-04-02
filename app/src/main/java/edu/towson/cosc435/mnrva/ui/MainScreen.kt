package edu.towson.cosc435.mnrva.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.mnrva.R
import edu.towson.cosc435.mnrva.ui.nav.MnrvaNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val nav = rememberNavController()

    // Scaffolding for the app
    Scaffold (
        topBar = { TopBar() },
        bottomBar = {}
    ){
        MnrvaNavGraph(nav)
    }

}

@Composable
private fun TopBar() {
    Text(stringResource(R.string.app_name))
}