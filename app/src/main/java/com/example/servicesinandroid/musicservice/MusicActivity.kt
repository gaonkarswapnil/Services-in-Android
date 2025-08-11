package com.example.servicesinandroid.musicservice

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicesinandroid.R

class MusicActivity : AppCompatActivity() {

    private var musicService: MusicService? = null
    private var isBound = false

    private val connection= object:ServiceConnection{
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            val binder = service as MusicService.MusicBlinder
            musicService = binder.getServices()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            isBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_music)

        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnPause = findViewById<Button>(R.id.btnPause)
        val btnStatus = findViewById<Button>(R.id.btnStatus)
        val txtStatus = findViewById<TextView>(R.id.txtStatus)


        btnPlay.setOnClickListener {
            if(isBound){
                musicService?.playMusic()
            }
        }

        btnPause.setOnClickListener {
            if(isBound){
                musicService?.stopMusic()
            }
        }

        btnStatus.setOnClickListener {
            if (isBound) {
                txtStatus.text = musicService?.getStatus()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Intent(this, MusicService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if(isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}