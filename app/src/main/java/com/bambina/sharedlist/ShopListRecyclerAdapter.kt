package com.bambina.sharedlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by hirono-mayuko on 2017/06/06.
 */
class ShopListRecyclerAdapter(val tasks : ArrayList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ShopListRecyclerAdapter.ViewHolder).setData(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: Task) {
            itemView.list_item_text.text = data.name
            itemView.done.isChecked = data.done
        }
    }
}
