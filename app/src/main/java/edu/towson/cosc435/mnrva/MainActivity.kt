package edu.towson.cosc435.mnrva

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.*
import androidx.datastore.preferences.preferencesDataStore
import edu.towson.cosc435.mnrva.ui.AuthScreen
import edu.towson.cosc435.mnrva.ui.MainScreen
import edu.towson.cosc435.mnrva.ui.authentication.AuthenticationViewModel
import edu.towson.cosc435.mnrva.ui.theme.MNRVATheme

private const val PREFS_KEY = "mnrva-preferences"
val Context.dataStore by preferencesDataStore(PREFS_KEY)

class MainActivity : ComponentActivity() {
    private val authenticationViewModel = AuthenticationViewModel()
    private val authenticated = authenticationViewModel.authenticated

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("MNRVA", "Setting app content.")
            MNRVATheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    if (authenticated.value) {
                        Log.d("MNRVA", "User is authenticated, showing MainScreen.")
                        MainScreen()
                    } else {
                        Log.d("MNRVA", "User is not authenticated, showing AuthScreen.")
                        AuthScreen(authenticationViewModel)
                    }
                }
            }
        }
    }
}