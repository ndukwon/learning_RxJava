package com.duk.lab.rxandroid.application

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView? = null
    var titleView: TextView? = null

    fun getClickObserver(item: RecyclerItem): Observable<RecyclerItem> {
        return Observable.create { emitter: ObservableEmitter<RecyclerItem> ->
            itemView.setOnClickListener {
                emitter.onNext(item)
            }
        }
    }
}