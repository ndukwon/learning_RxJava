package com.duk.lab.rxandroid.basic

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duk.lab.rxandroid.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.observers.DisposableObserver

import kotlinx.android.synthetic.main.fragment_rx_android_basic.*

/**
 * A placeholder fragment containing a simple view.
 */
class RxAndroidBasicActivityFragment : Fragment() {
//    companion object {
//        val TAG = RxAndroidBasicActivityFragment::class.simpleName
//    }
    val TAG = "RxAndroidBasicActivityFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rx_android_basic, container, false)
    }

    override fun onStart() {
        super.onStart()

        // Hello World!
        buttonEx2.setOnClickListener(this::playEx2)
        buttonEx3.setOnClickListener(this::playEx3)
        buttonEx4.setOnClickListener(this::playEx4)

        // Flow Control
        buttonEx5_1.setOnClickListener(this::playEx5_Java)
        buttonEx5_2.setOnClickListener(this::playEx5_RxJava1)
        buttonEx5_3.setOnClickListener(this::playEx5_RxJava2)

        // RxLifecycle
        buttonEx7.setOnClickListener {
            val intent = Intent(activity, HelloRxLifecycleActivity::class.java)
            startActivity(intent)
        }

        // Event Listener
        registerEventListener()
    }

    /*
        Hello World!
     */
    fun playEx2(view: View?) {
        Observable.create(ObservableOnSubscribe<String> {
            it.onNext("Hello World! Ex2")
            it.onComplete()
        }).subscribe(object: DisposableObserver<String>() {
            override fun onNext(str: String) {
                textViewEx2.text = str
            }

            override fun onError(e: Throwable) {
                // Do Nothing
            }

            override fun onComplete() {
                // Do Nothing
            }
        })
    }

    fun playEx3(view: View?) {
        Observable.create<String> {
            it.onNext("Hello World! Ex3")
            it.onComplete()
        }.subscribe { item -> textViewEx3.text = item }
    }

    fun playEx4(view: View?) {
        Observable.just("Hello World! Ex4")
                .subscribe { item -> textViewEx4.text = item }

    }

    /*
        Flow Control
     */
    val samples = listOf("banana", "orange", "apple", "apple mango", "melon", "watermelon")

    fun playEx5_Java(view: View?) {
        val sb = StringBuilder()
        sb.append("get an apple :: Java")
        sb.append("\n")

        for (item in samples) {
            if (item.contains("apple")) {
                sb.append("found apple")
                sb.append("\n")
                break;
            }
        }

        textViewEx5_1.text = sb
    }

    fun playEx5_RxJava1(view: View?) {
        // TODO: Implement RxJava1 code
    }

    fun playEx5_RxJava2(view: View?) {
        val sb = StringBuilder()
        sb.append("get an apple :: rx 2")
        sb.append("\n")
        Observable.fromIterable(samples)
                .filter{ it.contains("apple") }
                .first("Not found!")
                .subscribe { item ->
                    sb.append(item)
                    sb.append("\n")
                    textViewEx5_3.text = sb
                }
    }

    fun registerEventListener() {
        ex8_register()
        ex9_register()
        ex10_register()
    }

    fun ex8_register() {
        // Making a source
        val source = Observable.create { emitter: ObservableEmitter<View> ->
            //            buttonEx8.setOnClickListener { emitter::onNext }      // not working
            buttonEx8.setOnClickListener { view -> emitter.onNext(view) }
        }.map { item -> "buttonEx8 is clicked!" }

        // Making a subscription
        source.subscribe(object: DisposableObserver<String>() {
            override fun onNext(t: String) {
                textViewEx8.text = t
            }

            override fun onError(e: Throwable) {
                textViewEx8.text = e.toString()
            }

            override fun onComplete() {
                textViewEx8.text = "onComplete()"
            }
        })
    }

    fun ex9_register() {
        // Making a source
        val source = Observable.create { emitter: ObservableEmitter<View> ->
            buttonEx9.setOnClickListener { view -> emitter.onNext(view) }
        }.map { item -> "buttonEx9 with lambda is clicked!" }

        // Making a subscription
        source.subscribe { text -> textViewEx9.text = text }
    }

    fun ex10_register() {
        // Making a source
        val source = RxView.clicks(buttonEx10)
                .map { item -> "buttonEx10 with Rxbinding is clicked!" }

        // Making a subscription
        source.subscribe { text -> textViewEx10.text = text }
    }
}
