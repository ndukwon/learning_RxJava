package com.duk.lab.rxandroid.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.duk.lab.rxandroid.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {
    var timer:Timer? = null
    var countDownTimer: CountDownTimer? = null
    var handler:Handler? = null
    var disposable: Disposable? = null
    var disposable2: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        initialSetup()
    }

    fun initialSetup() {
        textView.text = "0"
        textView2.text = "0"
        textView3.text = "0"
        textView4.text = "0"
        textView5.text = "0"
        button.text = "Start Timer"
        button.setOnClickListener { startTimer() }
    }

    fun startTimer() {
        startTimer1()
        startTimer2()
        startTimer3()
        startTimer4()
        startTimer5()

        button.text = "Stop Timer"
        button.setOnClickListener { stopTimer() }
    }

    fun stopTimer() {
        stopTimer1()
        stopTimer2()
        stopTimer3()
        stopTimer4()
        stopTimer5()

        initialSetup()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopTimer()
    }

    // Timer #1
    fun startTimer1() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                runOnUiThread {
                    textView.text = Integer.toString(Integer.parseInt(textView.text.toString()) + 1)
                }
            }
        },
                0L,
                1000L
        )
    }

    fun stopTimer1() {
        timer?.cancel()
        timer = null
    }

    // Timer2
    fun startTimer2() {
        countDownTimer = object: CountDownTimer(1000L * 11, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                textView2.text = Integer.toString(Integer.parseInt(textView2.text.toString()) + 1)
            }

            override fun onFinish() {
            }
        }
        countDownTimer?.start()
    }

    fun stopTimer2() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    // Timer3
    fun startTimer3() {
        handler = Handler()
        triggerHandler()
    }

    fun triggerHandler() {
        handler?.postDelayed(this.runnable, 1000L)
    }

    val runnable = Runnable {
        textView3.text = Integer.toString(Integer.parseInt(textView3.text.toString()) + 1)
        triggerHandler()
    }

    fun stopTimer3() {
        handler?.removeCallbacks(runnable)
    }

    // Timer4
    fun startTimer4() {
        val source = Observable.interval(1L, TimeUnit.SECONDS)
        disposable = source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    textView4.text = "${it + 1}"
                }
    }

    fun stopTimer4() {
        disposable?.dispose()
        disposable = null
    }

    fun startTimer5() {
        val source = Observable.just("item")
                .repeatWhen {
                    it.delay(1L, TimeUnit.SECONDS)
                }
        disposable2 = source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    textView5.text = Integer.toString(Integer.parseInt(textView5.text.toString()) + 1)
                }
    }

    fun stopTimer5() {
        disposable2?.dispose()
        disposable2 = null
    }
}
