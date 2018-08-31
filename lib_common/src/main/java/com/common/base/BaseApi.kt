package com.common.base

import com.common.BuildConfig
import com.common.utils.MyGsonTypeAdapterFactory
import com.common.utils.cookie.CookiesManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

interface BaseApi {

    companion object {

        fun newApiService(cookieJar: CookiesManager, baseUrl: String): BaseApi {
            val gson = GsonBuilder().registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create()).create()
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addNetworkInterceptor(StethoInterceptor())
                    .cookieJar(cookieJar)
                    .build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create<BaseApi>(BaseApi::class.java)
        }
    }
}