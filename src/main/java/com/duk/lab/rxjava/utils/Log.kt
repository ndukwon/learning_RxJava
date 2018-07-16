package com.duk.lab.rxjava.utils

class Log {
    companion object {
        fun getThreadName(): String {
            var threadName = Thread.currentThread().name
            if (threadName.length > 30) {
                threadName = threadName.substring(0, 30) + "..."
            }
            return threadName
        }

        @JvmStatic
        fun println(obj: Any?) {
            System.out.println(getThreadName() + " | value = $obj")
        }
    }
}