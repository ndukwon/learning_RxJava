package com.duk.lab.rxandroid.application

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.duk.lab.rxandroid.R
import io.reactivex.subjects.PublishSubject

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewHolder>() {
    val itemList = mutableListOf<RecyclerItem>()
    var publishSubject: PublishSubject<RecyclerItem> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView?.setImageDrawable(item.image)
        holder.titleView?.text = item.title
        holder.getClickObserver(item)
                .subscribe(publishSubject)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addList(items: List<RecyclerItem>) {
        itemList.addAll(items)
    }

    fun addItem(item: RecyclerItem) {
        itemList.add(item)
    }
}