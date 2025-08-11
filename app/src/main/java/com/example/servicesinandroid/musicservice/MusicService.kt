package com.example.servicesinandroid.musicservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MusicService: Service() {

    val localBinder = MusicBlinder()
    var isPlaying = false

    inner class MusicBlinder: Binder(){
        fun getServices(): MusicService = this@MusicService
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d("MusicService", "Service Bound")
        return localBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MusicService", "Service unBound")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d("MusicService", "Service Destroyed")
        super.onDestroy()
    }

    fun playMusic(){
        isPlaying = true
        Log.d("MusicService", "Music Started")
    }

    fun stopMusic(){
        isPlaying = false
        Log.d("MusicService", "Music Stopped")
    }

    fun getStatus(): String{
        return if(isPlaying) "Playing" else "Stopped"
    }
}