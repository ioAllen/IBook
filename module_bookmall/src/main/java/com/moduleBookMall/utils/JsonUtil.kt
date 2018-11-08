package com.moduleBookMall.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.lang.reflect.Type

import javax.inject.Inject
import javax.inject.Singleton

/**
 * json工具类
 * Created by LiuSaibao on 10/10/2017.
 */

class JsonUtil @Inject
constructor() {

    val gson: Gson = GsonBuilder()
            .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
            .create()

    fun toJson(`object`: Any): String {
        return gson.toJson(`object`)
    }

    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }

    fun <T> fromJson(json: String, typeOfT: Type): T {
        return gson.fromJson<T>(json, typeOfT)
    }
}
