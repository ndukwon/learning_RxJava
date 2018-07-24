package com.duk.lab.rxjava.utils

import java.util.*

class TimeUtil {
    companion object {
        var startTime: Long = 0L

        @JvmStatic
        fun setStartTime() {
            startTime = System.currentTimeMillis()
        }

        @JvmStatic
        fun sleep(millis: Int) {
            try {
                Thread.sleep(millis.toLong())
            } catch (e:InterruptedException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun doSomething() {
            try {
                Thread.sleep(Random().nextInt(100).toLong())
            } catch (e:InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}