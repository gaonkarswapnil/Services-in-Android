package com.example.servicesinandroid.basicbackgroundservice

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicesinandroid.R

class BackgroundServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_background_service)
        val intent = Intent(this, BackgroundService::class.java)
        startService(intent) // Starts in background
    }
}