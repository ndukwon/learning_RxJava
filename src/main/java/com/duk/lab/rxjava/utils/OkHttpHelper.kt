package com.duk.lab.rxjava.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OkHttpHelper {
    companion object {
        val client = OkHttpClient()

        @JvmStatic
        fun run(url: String): String {
            val request = Request.Builder()
                    .url(url)
                    .build()

            try {
                val response = client.newCall(request).execute()
                return response.body()!!.string()
            } catch (e: IOException) {
                Log.println(e.message)
            }

            return ""
        }
    }
}