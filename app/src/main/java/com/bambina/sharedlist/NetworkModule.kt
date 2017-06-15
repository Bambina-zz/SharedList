package com.bambina.sharedlist

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by hirono-mayuko on 2017/06/05.
 */

@Module
class NetworkModule {
    val END_POINT = "https://shared-lists.herokuapp.com"

    @Provides @Singleton internal fun provideOkHttpClient() : OkHttpClient = OkHttpClient()

    @Provides @Singleton @Named("Api") internal fun provideRetrofit(client : OkHttpClient) : Retrofit {
        return Retrofit.Builder().baseUrl(END_POINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides @Singleton internal fun provideApi(@Named("Api") retrofit : Retrofit): SharedListApi {
        return SharedListApiClient(retrofit)
    }
}
