package com.bambina.sharedlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api : ShopListApi

    var d : Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                        onNext = { print(it.errand.name); print(it.tasks) },
                        onComplete = { println("Done") },
                        onError = { it.printStackTrace() }
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }
}
