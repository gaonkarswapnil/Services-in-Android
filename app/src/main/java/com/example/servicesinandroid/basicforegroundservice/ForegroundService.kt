package com.example.servicesinandroid.basicforegroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.servicesinandroid.R

class ForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val channelId = "my_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "My Foreground Service",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Service Running")
            .setContentText("Foreground service is active")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(1, notification)

        // Do your background work here...
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
