package com.common.base;

import android.app.Application;

/**
 * 用于代理 {@link Application} 的生命周期
 */
public interface AppLifeCycles {

    void onCreate(Application application);

}
