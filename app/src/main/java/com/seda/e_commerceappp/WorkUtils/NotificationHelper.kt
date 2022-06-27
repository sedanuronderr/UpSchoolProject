package com.seda.e_commerceappp.Utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.seda.e_commerceappp.Loginİslemler.MainActivity
import com.seda.e_commerceappp.R


class NotificationHelper(val context:Context) {

    private val CHANNEL_ID = "reminder_channel_id"
    private val NOTIFICATION_ID = 1
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT ).apply {
                description = "Reminder Channel Description"
            }
            val notificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title: String){
        // 1
        createNotificationChannel()
        // 2
        val intent = Intent(context, MainActivity:: class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        // 3
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        // 4
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.bag)
        // 5
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.bag)
            .setLargeIcon(icon)
            .setContentTitle(title)

            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        // 6
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }
}