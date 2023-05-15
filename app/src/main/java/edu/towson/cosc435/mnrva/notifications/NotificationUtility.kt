package edu.towson.cosc435.mnrva.notifications

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import edu.towson.cosc435.mnrva.MnrvaApplication
import edu.towson.cosc435.mnrva.R

class NotificationUtility {

    companion object{
        lateinit var notificationManager: NotificationManager
        const val channel_id = "ID_MNRVA"
        const val channel_name = "CHANNEL_MNRVA"


        fun CreateNotificationChannel(ctx: Context){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    channel_id,
                    channel_name,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Notification Channel for MNRVA"
                }
                notificationManager =
                    ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        fun CreateEventNotification(ctx: Context, title:String, description:String){

            Log.d("TEST", "creating notification....")
            //Create intent w/ flags that opens MnrvaApp
            val intent = Intent(ctx, MnrvaApplication::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            //Create the notification with a NotificationCompat Builder
            val notificationBuilder = NotificationCompat.Builder(ctx, channel_id).apply {
                setSmallIcon(R.drawable.notification_settings)

                setContentTitle(title)
                setContentText(description)

                priority = NotificationCompat.PRIORITY_DEFAULT
            }
            intent.putExtra(channel_name, notificationBuilder.build())

            //Create the pending intent
            val pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0)

            //Attatch the intent to the notification
            notificationBuilder.setContentIntent(pendingIntent)

            val alarmManager: AlarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent)
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, pendingIntent)
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, pendingIntent)
        }
    }
}