package com.duk.lab.rxandroid.application

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import com.duk.lab.rxandroid.application.recycler.RecyclerActivity
import com.duk.lab.rxandroid.application.restapi.RestApiActivity
import com.duk.lab.rxandroid.log.DLog

class ApplicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
    }

    fun gotoRecycler(view: View) {
        DLog.i( "gotoRecycler clicked!!")
        val intent = Intent()
        intent.setClass(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

    fun gotoAsync(view: View) {
        DLog.i( "gotoAsync clicked!!")
        val intent = Intent()
        intent.setClass(this, AsyncActivity::class.java)
        startActivity(intent)
    }

    fun gotoTimer(view: View) {
        DLog.i( "gotoTimer clicked!!")
        val intent = Intent()
        intent.setClass(this, TimerActivity::class.java)
        startActivity(intent)
    }

    fun gotoRestApi(view: View) {
        DLog.i( "gotoRestApi clicked!!")
        val intent = Intent()
        intent.setClass(this, RestApiActivity::class.java)
        startActivity(intent)
    }
}
