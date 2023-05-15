package edu.towson.cosc435.mnrva.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TEST", "testing broadcast reciever A")
        if (context != null && intent != null){
            Log.d("TEST", "testing broadcast reciever B")
        }

        if (intent != null){
            when(intent.action){
                "Notification" ->{
                    Log.d("TEST", "test broadcast c")
                }
            }
        }
    }
}