package com.bambina.sharedlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.bambina.sharedlist.R
import com.bambina.sharedlist.activity.MainActivity
import com.bambina.sharedlist.model.Errand
import kotlinx.android.synthetic.main.list_errand.view.*

/**
 * Created by hirono-mayuko on 2017/06/14.
 */
class ErrandRecyclerAdapter(val errands : Array<Errand>, val mainActivity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_errand, parent, false)
        return ViewHolder(view, mainActivity)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ViewHolder).setData(errands[position])
    }

    override fun getItemCount(): Int = errands.size

    class ViewHolder(itemView : View, val mainActivity: MainActivity) : RecyclerView.ViewHolder(itemView) {

        fun setData(errand : Errand){
            itemView.name.text = errand.name

            itemView.setOnTouchListener { _, event ->
                when(event.action){
                    MotionEvent.ACTION_UP -> {
                        mainActivity.addTaskFragment(errand.shared_hash)
                    }
                }
                false
            }
        }
    }
}
