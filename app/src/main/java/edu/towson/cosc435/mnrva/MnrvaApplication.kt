package edu.towson.cosc435.mnrva

import android.app.Application

class MnrvaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyGraph.provide(this)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = NotificationChannel(
//                channel_id,
//                channel_name,
//                NotificationManager.IMPORTANCE_DEFAULT
//            ).apply {
//                description = "Notification Channel for MNRVA"
//            }
//            notificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
    }

//    companion object{
//        lateinit var notificationManager: NotificationManager
//        const val channel_id = "ID_MNRVA"
//        const val channel_name = "CHANNEL_MNRVA"
//    }
}