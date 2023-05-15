package edu.towson.cosc435.mnrva.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import edu.towson.cosc435.mnrva.R
import java.time.Duration
import java.time.LocalDateTime

class NotificationUtility {

    companion object{
        lateinit var notificationManager: NotificationManager
        private const val channel_id = "ID_MNRVA"
        private const val channel_name = "CHANNEL_MNRVA"

        fun createNotificationChannel(ctx: Context){
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

        fun createEventNotification(ctx: Context, title:String, description:String, time: LocalDateTime){
            //Create intent w/ flags that opens MnrvaApp
            val intent = Intent(ctx, NotificationReceiver::class.java).apply {
                action = ctx.getString(R.string.notifications)
                type = "$title, $description"
                putExtra("Notification_Title", title)
                putExtra("Notification_Description", description)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            //Create the pending intent
            val pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val delay = Duration.between(LocalDateTime.now(), time).toMillis()

            val alarmManager: AlarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delay, pendingIntent)
        }

        fun sendNotification(ctx: Context, title: String, description: String){
            val notificationBuilder = NotificationCompat.Builder(ctx, channel_id).apply {
                setSmallIcon(R.drawable.notification_settings)
                setContentTitle(title)
                setContentText(description)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}