package com.common.base;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * 通用的响应
 * Created by LiuSaibao on 9/20/2017.
 */

@AutoValue
public abstract class Common extends Base {

    public static TypeAdapter<Common> typeAdapter(Gson gson) {
        return new AutoValue_Common.GsonTypeAdapter(gson);
    }
}
