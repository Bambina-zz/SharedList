package com.bambina.sharedlist

import io.reactivex.Observable

/**
 * Created by hirono-mayuko on 2017/06/02.
 */
interface ShopListApi {
    fun getErrandList(listUrl : String): Observable<ErrandList>

    fun updateTaskDone(errandId : String, taskId : String, done : HashMap<String, String>): Observable<Unit>
}
