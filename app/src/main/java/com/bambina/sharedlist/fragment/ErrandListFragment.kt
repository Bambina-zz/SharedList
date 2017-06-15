package com.bambina.sharedlist.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bambina.sharedlist.adapter.ErrandRecyclerAdapter
import com.bambina.sharedlist.activity.MainActivity
import com.bambina.sharedlist.R
import com.bambina.sharedlist.model.Errand
import kotlinx.android.synthetic.main.fragment_list_errand.*

/**
 * Created by hirono-mayuko on 2017/06/14.
 */
class ErrandListFragment(val errands : Array<Errand>, val mainActivity: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_list_errand, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing RecyclerView.
        recycler_view?.setHasFixedSize(true)
        recycler_view?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recycler_view?.adapter = ErrandRecyclerAdapter(errands, mainActivity)
    }
}
