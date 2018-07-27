package com.common.utils

import android.content.Context
import com.common.base.BaseApplication
import com.common.di.component.AppComponent

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
object CommonUtils {

    fun obtainAppComponentFromContext(context: Context): AppComponent {
        Preconditions.checkNotNull(context, "%s cannot be null", Context::class.java.name)
        return BaseApplication[context].getAppComponent()
    }
}