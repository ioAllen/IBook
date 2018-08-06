package com.common.utils.filedownload

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * author：WangLei
 * date:2018/2/6.
 * QQ:619321796
 */
class FileDownloadUtil
constructor(private val mContext: Context) {
    private var retrofit: Retrofit.Builder? = null
    private val baseUrlApp = "http://id:85/"
//    private val baseUrlApk = "http://118.24.53.144:8080/"

    fun fileDownloadFile(name: String, destFileDir: String, destFileName: String, onDownloadListener: OnDownloadListener) {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
        }
        retrofit!!.baseUrl(baseUrlApp)
                .client(initOkHttpClient(onDownloadListener)).build().create(IFileLoad::class.java).downloadFile(name)
                .enqueue(MyFileCallback(onDownloadListener, destFileDir, destFileName))
    }

    class MyFileCallback(private var onDownloadListen: OnDownloadListener?, destFileDir: String?, destFileName: String?) : FileCallback(destFileDir, destFileName) {
        override fun onSuccess(file: File) {
            if (onDownloadListen != null) {
                onDownloadListen!!.onDownloadSuccess()
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            if (onDownloadListen != null) {
                onDownloadListen!!.onDownloadFailure()
            }
        }
    }

    interface IFileLoad {

        @GET("/{fialeName}")
        fun downloadFile(@Path("fialeName") fileName: String): Call<ResponseBody>
    }

    private fun initOkHttpClient(onDownloadListener: OnDownloadListener?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(100000, TimeUnit.SECONDS)
        builder.networkInterceptors().add(Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            originalResponse
                    .newBuilder()
                    .body(FileResponseBody(originalResponse, mContext, onDownloadListener))
                    .build()
        })
        return builder.build()
    }

    interface OnDownloadListener {
        /**
         * 下载成功
         */
        fun onDownloadSuccess()

        /**
         * 下载进度
         */
        fun onDownloadProgress(process: Long, current: Long, total: Long)

        /**
         * 下载失败
         */
        fun onDownloadFailure()
    }
}