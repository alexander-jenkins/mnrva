package edu.towson.cosc435.mnrva.ui


import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import edu.towson.cosc435.mnrva.notifications.NotificationReciever
import edu.towson.cosc435.mnrva.ui.nav.MnrvaNavGraph
import edu.towson.cosc435.mnrva.ui.theme.MNRVATheme

class MainActivity : ComponentActivity() {

    private val reciever: NotificationReciever = NotificationReciever()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MNRVATheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) { MnrvaNavGraph() }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "registered receiever")
        registerReceiver(reciever, IntentFilter("Notification"))
    }
}