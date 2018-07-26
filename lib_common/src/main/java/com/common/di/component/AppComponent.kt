package com.common.di.component

import android.app.Application
import android.content.Context
import com.common.di.ApplicationContext
import com.common.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}