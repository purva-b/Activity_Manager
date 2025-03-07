package com.example.activity_manager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.activity_manager.R

class ActivityB : AppCompatActivity() {
    private var restartCounter = 0
    private lateinit var restartCounterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        restartCounterText = findViewById(R.id.restart_counter_b)
        val finishButton: Button = findViewById(R.id.finish_b)

        finishButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("INCREMENT", 5) // Increase by 5
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
