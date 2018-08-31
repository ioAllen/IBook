package com.common.base;

import android.support.annotation.Nullable;

/**
 * 通用的响应
 * Created by liusaibao on 17/09/2017.
 */

public abstract class Base {

    public abstract int code();

    @Nullable
    public abstract String message();
}
