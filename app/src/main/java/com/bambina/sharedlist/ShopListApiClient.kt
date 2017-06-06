package com.bambina.sharedlist

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by hirono-mayuko on 2017/06/05.
 */
class ShopListApiClient(retrofit : Retrofit) : ShopListApi {

    interface Service {
        @Headers("Accept: application/json")
        @GET("/errands/shared/{listUrl}")
        fun getErrandList(@Path("listUrl") listUrl: String): Observable<ErrandList>
    }

    var service : Service = retrofit.create(Service::class.java)

    override fun getErrandList(listUrl: String): Observable<ErrandList> {
       return service.getErrandList(listUrl)
    }
}
