package com.duk.lab.rxjava.utils

class TimeUtil {
    companion object {
        var startTime: Long = 0L

        @JvmStatic
        fun setStartTime() {
            startTime = System.currentTimeMillis()
        }

        @JvmStatic
        fun sleep(millis: Int) {
            Thread.sleep(millis.toLong())
        }
    }
}