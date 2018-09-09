package com.duk.lab.rxandroid.application.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.duk.lab.rxandroid.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = itemView.findViewById(R.id.itemImage)
    val titleView: TextView = itemView.findViewById(R.id.itemText)

    fun getClickObserver(item: RecyclerItem): Observable<RecyclerItem> {
        return Observable.create { emitter: ObservableEmitter<RecyclerItem> ->
            itemView.setOnClickListener {
                emitter.onNext(item)
            }
        }
    }
}