package com.common.di.module

import android.app.Application
import android.content.Context
import com.common.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Module
class ApplicationModule(private val mApplication: Application) {
    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }
}