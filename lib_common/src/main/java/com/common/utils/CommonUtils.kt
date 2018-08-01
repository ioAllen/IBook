package com.common.utils

import android.content.Context
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.common.base.BaseApplication
import com.common.di.component.AppComponent

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
object CommonUtils {

    /**
     * 获得application的Component
     */
    fun obtainAppComponentFromContext(context: Context?): AppComponent {
        Preconditions.checkNotNull(context, "%s cannot be null", Context::class.java.name)
        return BaseApplication[context].getAppComponent()
    }

    /**
     * 通过路径进行跳转到新的界面
     */
    fun navigation(context: Context?, path: String) {
        Preconditions.checkNotNull(path, "path不能为空")
        ARouter.getInstance().build(path).navigation(context)
    }

    fun navigation(path: String) {
        navigation(null, path)
    }

    fun navigationPostcard(path: String): Postcard {
        Preconditions.checkNotNull(path, "path不能为空")
        return ARouter.getInstance().build(path)
    }

    /**
     * 通过path加载一个fragment
     */
    fun obtainARouterFragment(path: String): Fragment? {
        Preconditions.checkNotNull(path, "Fragment路径不能为空")
        val obj = ARouter.getInstance().build(path).navigation() ?: return null
        Preconditions.checkArgument(obj is Fragment, "加载目标不是Fragment")
        return obj as Fragment
    }
}