package com.duk.lab.rxandroid.application.restapi.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.duk.lab.rxandroid.log.DLog
import io.reactivex.Observable
import org.json.JSONObject
import java.util.concurrent.ExecutionException

class RequestHelper {
    val requestQueue: RequestQueue

    constructor(context: Context) {
        requestQueue = Volley.newRequestQueue(context)
    }

    fun getFuture(url: String): RequestFuture<JSONObject> {
        val requestFuture = RequestFuture.newFuture<JSONObject>()
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null, requestFuture, requestFuture)
        requestQueue.add(jsonRequest)

        return requestFuture
    }

    fun getData(url: String): JSONObject {
        return getFuture(url).get()
    }

    // 구독할때 마다defer를 새로 ObservableSource 구간을 새로 생성한다
    fun requestByObserver(url: String): Observable<JSONObject> {
        val source = Observable.defer {
            var result: Observable<JSONObject>
            try {
                result = Observable.just(getData(url))
                DLog.i( "getData is successful!!")
            } catch (e: InterruptedException) {
                DLog.i( "InterruptedException occurred!!")
                result = Observable.error(e.cause)
            } catch (e: ExecutionException) {
                DLog.i( "ExecutionException occurred!!")
                result = Observable.error(e.cause)
            }

            result
        }

        return source
    }

    fun requestByCallable(url: String): Observable<JSONObject> {
        val source = Observable.fromCallable {
            getData(url)
        }

        return source
    }

    fun requestByFuture(url: String): Observable<JSONObject> {
        val source = Observable.fromFuture(getFuture(url))

        return source
    }
}