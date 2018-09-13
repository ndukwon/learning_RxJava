package com.duk.lab.rxandroid.application.restapi.retrofit2

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duk.lab.rxandroid.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrofit2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Retrofit2Activity : AppCompatActivity() {
    val restAdapter: GithubRestAdapter = GithubRestAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)
    }

    fun test(view: View) {
        val service = restAdapter.getSimpleApi()
        val call = service.listRepos("ndukwon")
        call.enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    val repos = response.body()
                    textView.text = repos?.joinToString()
                }
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                textView.text = "API failed!!"
            }
        })
    }

    fun withRxJava(view: View) {
        val service = restAdapter.getServiceApi()
        val source = service.getObservableContributors("ndukwon", "learning_RxJava")

        val disposable = source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<List<Contributor>>() {
                    override fun onNext(item: List<Contributor>) {
                        textView.text = item.joinToString()
                    }

                    override fun onError(e: Throwable) {
                        textView.text = "Error"
                    }

                    override fun onComplete() {
                    }
                })

    }

    fun justRetrofit2(view: View) {
        val service = restAdapter.getServiceApi()
        val call = service.getCallContributors("ndukwon", "learning_RxJava")
        call.enqueue(object: Callback<List<Contributor>> {
            override fun onResponse(call: Call<List<Contributor>>, response: Response<List<Contributor>>) {
                if (response.isSuccessful) {
                    val contributors = response.body()
                    textView.text = contributors?.joinToString()
                }
            }

            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                textView.text = "API failed!!"
            }
        })
    }

    fun withFuture(view: View) {
        if (Build.VERSION.SDK_INT >= 24) {
            val service = restAdapter.getServiceApiWithFuture()
            val future = service.getFutureContributorsWithHeader("ndukwon", "learning_RxJava")

            val contributors = future.get()
            textView.text = contributors?.joinToString()
        } else {
            textView.text = "Try this in Java8"
        }
    }
}
