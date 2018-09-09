package com.duk.lab.rxandroid.application.restapi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import com.duk.lab.rxandroid.application.restapi.volley.VolleyActivity
import com.duk.lab.rxandroid.log.DLog

class RestApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_api)
    }

    fun gotoVolley(view: View) {
        DLog.i( "gotoVolley clicked!!")
        val intent = Intent()
        intent.setClass(this, VolleyActivity::class.java)
        startActivity(intent)
    }
}
