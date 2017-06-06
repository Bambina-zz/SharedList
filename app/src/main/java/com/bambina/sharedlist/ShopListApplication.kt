package com.bambina.sharedlist

import android.app.Application

/**
 * Created by hirono-mayuko on 2017/06/05.
 */
class ShopListApplication : Application() {
    companion object {
        lateinit var application : ShopListApplication
    }

    val component : AppComponent by lazy {
        DaggerAppComponent.builder().networkModule(getNetworkModule()).build()
    }

    override fun onCreate() {
        super.onCreate()
        ShopListApplication.application = this
    }

    fun getNetworkModule(): NetworkModule = NetworkModule()
}
