package com.duk.lab.rxandroid.application.recycler

import android.content.Intent
import android.content.pm.ResolveInfo
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.duk.lab.rxandroid.R
import com.duk.lab.rxandroid.log.DLog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_recycler.*

/**
 * A placeholder fragment containing a simple view.
 */
class RecyclerActivityFragment : Fragment() {

    var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // RecyclerView Layout의 구성
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // RecyclerView의 Adapter
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter?.publishSubject
                ?.subscribe { item ->
                    Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show()
                }
    }

    override fun onStart() {
        super.onStart()

        // Making source
        val source = getPackageListByObservable()

        // Making subscription
        subscribeObservable(source)
    }

    fun getPackageListByObservable(): Observable<RecyclerItem> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val pm = activity?.packageManager
        return Observable.fromIterable(pm?.queryIntentActivities(intent, 0))
                .sorted(ResolveInfo.DisplayNameComparator(pm))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map {
                    RecyclerItem(
                            it.activityInfo.loadIcon(pm),
                            it.activityInfo.loadLabel(pm).toString()
                    )
                }
                .doOnNext {item ->  DLog.i(item.title) }
    }

    fun subscribeObservable(source: Observable<RecyclerItem>) {
        source.observeOn(AndroidSchedulers.mainThread())
                .doOnNext {item -> DLog.i(item.title) }
                .subscribe{
                    recyclerViewAdapter?.addItem(it)
                    recyclerViewAdapter?.notifyDataSetChanged()
                }
    }
}
