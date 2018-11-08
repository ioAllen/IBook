package com.moduleBookMall.data.remote

import com.common.utils.CommonConstant
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface MallApi {
//    /**
//     * 加载分类列表
//     */
//    @POST("book/loadBookByType")
//    fun loadBookByType(@QueryMap params: Map<String, String>): Observable<BookData>

    /**
     * 加载分类列表
     */
    @POST("book/loadBookByType")
    fun loadBookByType(@QueryMap params: Map<String, String>): Observable<ResponseBody>


    companion object {
        const val BASE_URL_APP = "http://" + CommonConstant.appIp + "/"
    }

}
