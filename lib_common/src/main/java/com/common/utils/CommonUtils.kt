package com.common.utils

import android.content.Context
import com.common.base.App
import com.common.di.component.AppComponent

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
object CommonUtils {

    fun obtainAppComponentFromContext(context: Context): AppComponent {
        Preconditions.checkNotNull(context, "%s cannot be null", Context::class.java.name)
        Preconditions.checkState(context.applicationContext is App, "Application does not implements App")
        return (context.applicationContext as App).getAppComponent()
    }
}