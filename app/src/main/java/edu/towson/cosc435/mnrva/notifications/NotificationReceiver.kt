package edu.towson.cosc435.mnrva.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null){
            val title = intent.getStringExtra("Notification_Title")
            val description = intent.getStringExtra("Notification_Description")
            Log.d("NOTIFICATION", "Notification received with title: $title, and description: $description")
            NotificationUtility.sendNotification(context, title!!, description!!)
        }
    }
}