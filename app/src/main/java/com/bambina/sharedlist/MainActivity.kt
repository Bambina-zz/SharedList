package com.bambina.sharedlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api : ShopListApi

    private var d : Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        (application as ShopListApplication).component.inject(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        queryErrandList()
    }

    private fun queryErrandList(){
        api.getErrandList("3441433b-81df-4092-aa31-54863797afb4")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { setData(it) },
                        onComplete = { println("Done") },
                        onError = { it.printStackTrace() }
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }

    private fun setData(data : ErrandList){
        runOnUiThread {
            title = data.errand.name

            data.tasks.sortBy { it.name }
            recycler_view.adapter = ShopListRecyclerAdapter(data.tasks)
        }
    }
}
