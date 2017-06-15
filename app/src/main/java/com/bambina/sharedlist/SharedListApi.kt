package com.bambina.sharedlist

import com.bambina.sharedlist.model.ErrandList
import io.reactivex.Observable

/**
 * Created by hirono-mayuko on 2017/06/02.
 */
interface SharedListApi {
    fun getErrandList(listUrl : String): Observable<ErrandList>

    fun updateTaskDone(errandId : String, taskId : String, done : HashMap<String, String>): Observable<Unit>
}
