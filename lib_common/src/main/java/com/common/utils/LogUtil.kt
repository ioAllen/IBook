package com.common.utils

import org.jetbrains.annotations.NonNls
import timber.log.Timber

/**
 * author：WangLei
 * date:2018/6/8
 * QQ:619321796
 */
object LogUtil {
    private const val isShow = true

    fun v(@NonNls message: String, vararg args: Any) {
        if (isShow) {
            Timber.v(message, args)
        }
    }

    fun d(@NonNls message: String, vararg args: Any) {
        if (isShow) {
            Timber.d(message, args)
        }
    }

    fun i(@NonNls message: String, vararg args: Any) {
        if (isShow) {
            Timber.i(message, args)
        }
    }

    fun w(@NonNls message: String, vararg args: Any) {
        if (isShow) {
            Timber.w(message, args)
        }
    }

    fun e(@NonNls message: String, vararg args: Any) {
        if (isShow) {
            Timber.e(message, args)
        }
    }

}