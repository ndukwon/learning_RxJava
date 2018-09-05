package com.duk.lab.rxandroid.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import kotlinx.android.synthetic.main.activity_async.*

class AsyncActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
    }

    fun playEx20(view: View) {
        AsyncUpdater(textView1).executeEx20()
    }

    fun playEx21(view: View) {
        AsyncUpdater(textView2).executeEx21()
    }

    fun playEx22(view: View) {

    }
}
