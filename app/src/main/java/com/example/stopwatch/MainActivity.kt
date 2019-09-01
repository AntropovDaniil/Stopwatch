package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running = false
    private var wasRunning = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }

        runTimer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning)  running = true
    }


    fun onClickStart(view: View){
        running = true
    }


    fun onClickStop(view: View){
        running = false
    }


    fun onClickReset(view: View){
        running = false
        seconds = 0
    }

    fun runTimer(){
        val handler = Handler()
        handler.post(object : Runnable{
            override fun run(){
                var hours = seconds/3600
                var minutes = (seconds%3600)/60
                var secs = seconds%60
                var time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs)
                time_view.setText(time)
                if (running) seconds++
                handler.postDelayed(this, 1000)
            }
        })
    }


}
