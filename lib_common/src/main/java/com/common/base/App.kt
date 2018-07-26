package com.common.base

import com.common.di.component.AppComponent

interface App {
    fun getAppComponent(): AppComponent
}