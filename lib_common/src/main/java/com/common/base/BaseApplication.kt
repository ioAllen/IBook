package com.common.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.common.BuildConfig
import com.common.di.component.AppComponent
import com.common.di.component.DaggerAppComponent
import com.common.di.module.ApplicationModule
import com.common.utils.Preconditions

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
class BaseApplication : Application() {

    lateinit var mHandler: Handler
    private var mAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        mHandler = Handler(mainLooper)
        mAppComponent = DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {     // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }


    fun getAppComponent(): AppComponent {
        Preconditions.checkNotNull(mAppComponent, "%s cannot be null", "mAppComponent")
        return mAppComponent!!
    }

    companion object {
        operator fun get(context: Context?): BaseApplication {
            return context?.applicationContext as BaseApplication
        }
    }
}