package com.example.activity_manager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var restartCounter = 0
    private lateinit var restartCounterText: TextView
    private lateinit var threadCounterText: TextView
    private var threadCounter = 0
    private var isRunning = true
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        const val REQUEST_CODE_B = 1
        const val REQUEST_CODE_C = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restartCounterText = findViewById(R.id.restart_counter)
        threadCounterText = findViewById(R.id.thread_counter)
        val startB: Button = findViewById(R.id.start_activity_b)
        val startC: Button = findViewById(R.id.start_activity_c)
        val showDialog: Button = findViewById(R.id.dialog_button)
        val closeApp: Button = findViewById(R.id.close_app)

        startB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivityForResult(intent, REQUEST_CODE_B) // Start Activity B
        }

        startC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivityForResult(intent, REQUEST_CODE_C) // Start Activity C
        }

        showDialog.setOnClickListener {
            showDialog()
        }

        closeApp.setOnClickListener {
            finishAffinity() // Closes the app
        }

        // Start background thread
        startCounterThread()
    }

    private fun startCounterThread() {
        Thread {
            while (isRunning) {
                Thread.sleep(1000)
                handler.post {
                    threadCounterText.text = "Thread Counter: $threadCounter"
                }
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
        restartCounter++
        restartCounterText.text = "onRestart() Count: $restartCounter"
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
        startCounterThread()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val increment = data?.getIntExtra("INCREMENT", 0) ?: 0
            threadCounter += increment
            threadCounterText.text = "Thread Counter: $threadCounter"
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Simple Dialog")
            .setMessage("This is a simple dialog.")
            .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
