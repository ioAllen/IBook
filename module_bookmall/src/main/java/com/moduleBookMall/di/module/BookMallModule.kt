package com.moduleBookMall.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import com.common.di.ActivityContext
import com.common.di.ApplicationContext
import com.common.utils.ApiUtils
import com.common.utils.cookie.CookiesManager
import com.google.gson.GsonBuilder
import com.moduleBookMall.data.remote.MallApi
import com.moduleBookMall.utils.MyGsonTypeAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BookMallModule(private val mActivity: Activity) {

    @Provides
    internal fun provideAPI(): MallApi {
        val gson = GsonBuilder().registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create()).create()
        return ApiUtils.newApiService(MallApi::class.java, gson, CookiesManager(mActivity), MallApi.BASE_URL_APP)
    }


}