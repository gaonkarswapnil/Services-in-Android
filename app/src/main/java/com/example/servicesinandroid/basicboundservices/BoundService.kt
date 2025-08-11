package com.example.servicesinandroid.basicboundservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class BoundService: Service() {

    private val binder = LocalBinder()
    private var counter = 0;

    inner class LocalBinder: Binder(){
        fun getService(): BoundService = this@BoundService
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("BoundServices", "Service Bound")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("BoundServices", "Service unbound")
        return super.onUnbind(intent)
    }

    fun getNextNumber(): Int{
        counter++
        return counter
    }
}