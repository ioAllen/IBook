package com.common.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.common.di.component.AppComponent

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
class BaseApplication : Application(), App {

    private lateinit var mAppDelegate: AppDelegate
    lateinit var mHandler: Handler

    override fun onCreate() {
        super.onCreate()
        this.mAppDelegate = AppDelegate(this)
        this.mAppDelegate.onCreate()
        mHandler = Handler(mainLooper)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }


    override fun getAppComponent(): AppComponent {
        return (mAppDelegate as App).getAppComponent()
    }

    companion object {
        operator fun get(context: Context?): BaseApplication {
            return context?.applicationContext as BaseApplication
        }
    }
}