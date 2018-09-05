package com.duk.lab.rxandroid.application

import android.os.AsyncTask
import android.widget.TextView
import com.duk.lab.rxandroid.log.DLog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class AsyncUpdater(val targetTextView: TextView) {

    // By AsyncTask
    fun executeEx20() {
        AsyncTaskExample().execute("Hello", "async", "world", "!!")
    }

    inner class AsyncTaskExample: AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            val sb = StringBuilder()
            for (item in params) {
                sb.append(item).append(" ")
            }

            return sb.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            targetTextView.text = result
        }
    }

    // By Observable
    fun executeEx21() {
        Observable.just("Hello", "rx", "world", "!!!")
                .reduce { accumulated, current -> "$accumulated $current" }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { targetTextView.text = it },
                        { e -> DLog.i(e.message) },
                        { DLog.i("onComplete()") }
                )
    }
}