package com.example.activity_manager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityC : AppCompatActivity() {
    private var restartCounter = 0
    private lateinit var restartCounterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        restartCounterText = findViewById(R.id.restart_counter_c)
        val finishButton: Button = findViewById(R.id.finish_c)

        finishButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("INCREMENT", 10) // Increase by 10
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        restartCounter++
        restartCounterText.text = "onRestart() Count: $restartCounter"
    }
}
