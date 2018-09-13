package com.duk.lab.rxandroid.application.restapi.volley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.duk.lab.rxandroid.R
import com.duk.lab.rxandroid.log.DLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_volley.*
import org.json.JSONObject
import io.reactivex.observers.DisposableObserver

class VolleyActivity : AppCompatActivity() {

//    val URL = "https://api.github.com"
    val URL = "http://time.jsontest.com/"
    var requestHelper: RequestHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)

        requestHelper = RequestHelper(applicationContext)
    }

    // FIXME: Please fix ANR after requests
    fun requestByObserver(view: View) {
        DLog.i( "requestByObserver clicked!!")

        val source = requestHelper?.requestByObserver(URL)
        DLog.i( "source = $source")
        source?.subscribeOn(Schedulers.io())
        source?.observeOn(AndroidSchedulers.mainThread())
        source?.subscribeWith(getObserver(textView1))
    }

    // FIXME: Please fix ANR after requests
    fun requestByCallable(view: View) {
        DLog.i( "requestByCallable clicked!!")

        val source = requestHelper?.requestByCallable(URL)
        DLog.i( "source = $source")
        source?.subscribeOn(Schedulers.io())
        source?.observeOn(AndroidSchedulers.mainThread())
        source?.subscribeWith(getObserver(textView2))
    }

    // FIXME: Please fix ANR after requests
    fun requestByFuture(view: View) {
        DLog.i( "requestByFuture clicked!!")

        val source = requestHelper?.requestByFuture(URL)
        DLog.i( "source = $source")
        source?.subscribeOn(Schedulers.io())
        source?.observeOn(AndroidSchedulers.mainThread())
        source?.subscribeWith(getObserver(textView3))
    }

    private fun getObserver(textView: TextView): DisposableObserver<JSONObject> {
        return object : DisposableObserver<JSONObject>() {
            override fun onNext(jsonObject: JSONObject) {
                textView.text = jsonObject.toString()
            }

            override fun onError(t: Throwable) {
                textView.text = t.toString()
            }

            override fun onComplete() {
                textView.text = textView.text.toString() + "\ncompleted"
            }
        }
    }
}
