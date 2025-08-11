package com.example.servicesinandroid.basicboundservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servicesinandroid.R

class BoundServiceActivity : AppCompatActivity() {

    private var myService: BoundService? = null
    private var isBound = false

    private val connection = object: ServiceConnection{
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            val binder = service as BoundService.LocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            myService =null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bound_service)


        val btnGetNumber = findViewById<Button>(R.id.btnGetNumber)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)

        btnGetNumber.setOnClickListener {
            if(isBound){
                val number = myService?.getNextNumber()
                tvOutput.text = "Counter $number"
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Intent(this, BoundService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if(isBound){
            unbindService(connection)
            isBound = false
        }
    }
}