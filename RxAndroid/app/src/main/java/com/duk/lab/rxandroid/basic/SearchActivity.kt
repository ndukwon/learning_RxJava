package com.duk.lab.rxandroid.basic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.duk.lab.rxandroid.R
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        ex12_register()
        ex13_register()
    }

    fun ex12_register() {
        // Making source
        val source = Observable.create { emitter: ObservableEmitter<CharSequence> ->
            editText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(p0!!)
                }
            })
        }

        // Making subscription
        source.debounce(500, TimeUnit.MILLISECONDS)
                .filter { word -> !TextUtils.isEmpty(word) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<CharSequence>() {
                    override fun onNext(t: CharSequence) {
                        textView.text = "Search $t"
                    }

                    override fun onError(e: Throwable) {
                        textView.text = e.toString()
                    }

                    override fun onComplete() {
                        textView.text = "onComplete()"
                    }
                })
    }

    private fun ex13_register() {
        // Making source
        val source = RxTextView.textChangeEvents(editText2)

        // Making subscription
        source.debounce(400, TimeUnit.MILLISECONDS)
                .filter { event -> !TextUtils.isEmpty(event.text()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<TextViewTextChangeEvent>() {
                    override fun onNext(t: TextViewTextChangeEvent) {
                        textView2.text = "Search ${t.text()}"
                    }

                    override fun onError(e: Throwable) {
                        textView2.text = e.toString()
                    }

                    override fun onComplete() {
                        textView2.text = "onComplete()"
                    }
                })
    }
}
