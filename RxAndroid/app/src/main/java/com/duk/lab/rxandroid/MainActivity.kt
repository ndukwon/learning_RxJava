package com.duk.lab.rxandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun gotoBasics(view: View?) {
        Log.i("MainActivity", "gotoBasics clicked!!")
        val intent = Intent()
        intent.setClass(this, RxAndroidBasicActivity::class.java)
        startActivity(intent)
    }

    fun gotoApplications(view: View?) {
        Log.i("MainActivity", "gotoApplications clicked!!")
    }

    fun gotoMemoryLeakCases(view: View?) {
        Log.i("MainActivity", "gotoMemoryLeakCases clicked!!")
    }
}
