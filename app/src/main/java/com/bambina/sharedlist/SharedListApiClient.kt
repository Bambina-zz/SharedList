package com.bambina.sharedlist

import com.bambina.sharedlist.model.ErrandList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.*

/**
 * Created by hirono-mayuko on 2017/06/05.
 */
class SharedListApiClient(retrofit : Retrofit) : SharedListApi {

    interface Service {
        @Headers("Accept: application/json")
        @GET("/shared_errands/{listUrl}")
        fun getErrandList(@Path("listUrl") listUrl: String): Observable<ErrandList>

        @Headers("Accept: application/json", "Content-type: application/json")
        @PUT("errands/{errandId}/tasks/{taskId}")
        fun updateTaskDone(@Path("errandId") errandId: String,
                           @Path("taskId") taskId: String,
                           @Body done: HashMap<String, String>): Observable<Unit>
    }

    var service : Service = retrofit.create(Service::class.java)

    override fun getErrandList(listUrl: String): Observable<ErrandList> {
       return service.getErrandList(listUrl)
    }

    override fun updateTaskDone(errandId: String, taskId: String, done: HashMap<String, String>): Observable<Unit> {
        return service.updateTaskDone(errandId, taskId, done)
    }
}
