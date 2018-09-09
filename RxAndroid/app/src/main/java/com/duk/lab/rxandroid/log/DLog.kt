package com.duk.lab.rxandroid.log

import android.util.Log

class DLog {
    companion object {
        fun i(info: String?) {
            val tag = "${Thread.currentThread().getStackTrace()[3].methodName} ${Thread.currentThread().name}"
            Log.i(tag, info)
        }
    }
}