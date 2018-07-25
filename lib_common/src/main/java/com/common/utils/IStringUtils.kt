package com.common.utils

import android.content.Context

object IStringUtils {

    /**
     * 是否为空或空字符串
     */
    fun isNullOrEmpty(str: String?): Boolean {
        return null == str || "null" == str || str.trim { it <= ' ' }.isEmpty()
    }

    /**
     * 根据分号拆分src
     */
    fun srcSplit(str: String, sign: String): Array<String> {
        return if (!isNullOrEmpty(str) && str.contains(sign)) {
            str.split(sign.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else arrayOf(str)
    }

    /**
     * 取得一个string字符串防止空值
     */
    fun getString(value: String): String {
        return if (!isNullOrEmpty(value)) {
            value
        } else ""
    }

    fun showToast(context: Context, msg: String) {
        ToastHelper.show(context, msg)
    }

    fun showToast(context: Context, msg: Int) {
        ToastHelper.show(context, msg)
    }


}
