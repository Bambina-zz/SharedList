package com.bambina.sharedlist.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.bambina.sharedlist.R
import com.bambina.sharedlist.SharedListApi
import com.bambina.sharedlist.SharedListApplication.Companion.application
import com.bambina.sharedlist.adapter.TaskRecyclerAdapter
import com.bambina.sharedlist.model.ErrandList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_list_errand.*
import javax.inject.Inject

/**
 * Created by hirono-mayuko on 2017/06/15.
 */
class TaskListFragment(val errandHash : String) : Fragment(){

    @Inject
    lateinit var api : SharedListApi
    private var d : Disposable = Disposables.empty()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        application.component.inject(this)
        return inflater?.inflate(R.layout.fragment_list_task, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting RecyclerView.
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recycler_view.setHasFixedSize(true)

        // Querying task data.
        queryListData()
    }

    private fun queryListData(){
        api.getErrandList(errandHash)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { setData(it) },
                        onComplete = { println("Done") },
                        onError = { displayToast(it.toString()) }
                )
    }

    private fun setData(data : ErrandList){
        activity.runOnUiThread {
            activity.title = data.errand.name

            data.tasks.sortBy { it.name }
            recycler_view.adapter = TaskRecyclerAdapter(data, context)
        }
    }

    private fun displayToast(msg : String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }
}
