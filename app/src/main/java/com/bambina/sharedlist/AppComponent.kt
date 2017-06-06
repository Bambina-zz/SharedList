package com.bambina.sharedlist


import dagger.Component
import javax.inject.Singleton
/**
 * Created by hirono-mayuko on 2017/06/05.
 */

@Component(modules = arrayOf( NetworkModule::class ))
@Singleton
interface AppComponent {
    fun api(): ShopListApi

    fun inject(activity : MainActivity)
}
