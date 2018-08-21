package com.duk.lab.rxandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import com.duk.lab.rxandroid.application.RecyclerActivity
import com.duk.lab.rxandroid.basic.RxAndroidBasicActivity

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
        val intent = Intent()
        intent.setClass(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

    fun gotoMemoryLeakCases(view: View?) {
        Log.i("MainActivity", "gotoMemoryLeakCases clicked!!")
    }
}
