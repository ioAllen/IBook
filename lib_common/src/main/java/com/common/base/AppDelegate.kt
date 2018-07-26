package com.common.base

import android.app.Application
import com.common.di.component.AppComponent
import com.common.di.component.DaggerAppComponent
import com.common.di.module.ApplicationModule
import com.common.utils.Preconditions

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
class AppDelegate constructor(protected var mApplication: Application) : App {

    private var mAppComponent: AppComponent? = null

    override fun getAppComponent(): AppComponent {
        Preconditions.checkNotNull(mAppComponent, "%s cannot be null,first call %s#onCreate(Application) in %s#onCreate()", AppComponent::class.java.name, javaClass.name, Application::class.java.name)
        return mAppComponent!!
    }


    fun onCreate() {
        mAppComponent = DaggerAppComponent.builder().applicationModule(ApplicationModule(mApplication)).build()
    }


}