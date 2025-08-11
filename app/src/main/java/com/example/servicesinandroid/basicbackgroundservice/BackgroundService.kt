package com.example.servicesinandroid.basicbackgroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.delay

class BackgroundService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)

        Thread {
            try {
                for (i in 1..10) {
                    Log.d("MyService", "Running... $i")
                    Thread.sleep(1000)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            stopSelf()
        }.start()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}