package com.bambina.sharedlist

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by hirono-mayuko on 2017/06/02.
 */
interface ShopListApi {
    @Headers("Accept: application/json")
    @GET("/errands/shared/{listUrl}")
    fun getErrandList(@Path("listUrl") listUrl: String): Observable<ErrandList>
}
