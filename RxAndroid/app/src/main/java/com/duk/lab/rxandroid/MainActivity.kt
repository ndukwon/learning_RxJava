package com.duk.lab.rxandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.learning_rxandroid.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // FIXME: Plz learn ButterKnife or DataBinding(https://developer.android.com/topic/libraries/data-binding/?hl=ko)
    fun gotoBasics(view: View?) {
        var intent = Intent()
        intent.setClassName(this, "RxAndroidBasicActivity")
        startActivity(intent)
    }

    fun gotoApplications(view: View) {
    }

    fun gotoMemoryLeakCases(view: View) {
    }
}
