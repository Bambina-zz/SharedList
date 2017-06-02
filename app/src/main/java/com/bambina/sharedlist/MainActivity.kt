package com.bambina.sharedlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val END_POINT = "https://shared-lists.herokuapp.com"
    var d : Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queryErrandList()
    }

    private fun queryErrandList(){
        val gson : Gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(END_POINT)
                .build()

        val shopListApi : ShopListApi = retrofit.create(ShopListApi::class.java)

        shopListApi.getErrandList("3441433b-81df-4092-aa31-54863797afb4")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { print(it.errand.name); print(it.errand.shared_hash) },
                        onComplete = { println("Done") },
                        onError = { it.printStackTrace() }
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }
}
