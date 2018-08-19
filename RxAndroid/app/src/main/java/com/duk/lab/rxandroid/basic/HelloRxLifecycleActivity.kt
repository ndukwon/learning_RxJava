package com.duk.lab.rxandroid.basic

import android.os.Bundle
import android.os.PersistableBundle
import com.duk.lab.rxandroid.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable

import kotlinx.android.synthetic.main.activity_hello_rx_lifecycle.*

class HelloRxLifecycleActivity: RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_rx_lifecycle)

        Observable.just("Hello RxLifecycle!")
                .compose(bindToLifecycle())
                .subscribe(textView::setText)
    }
}