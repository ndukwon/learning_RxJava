package com.duk.lab.rxandroid.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {
    var timer:Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        initialSetup()
    }

    fun initialSetup() {
        textView.text = "0"
        button.text = "Start Timer"
        button.setOnClickListener { startTimer() }
    }

    fun startTimer() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object: TimerTask() {
                    override fun run() {
                        runOnUiThread { updateText() }
                    }
                },
                0L,
                1000L
        )

        button.text = "Stop Timer"
        button.setOnClickListener { stopTimer() }
    }

    fun updateText() {
        textView.text = Integer.toString(Integer.parseInt(textView.text.toString()) + 1)
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null

        initialSetup()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopTimer()
    }
}
