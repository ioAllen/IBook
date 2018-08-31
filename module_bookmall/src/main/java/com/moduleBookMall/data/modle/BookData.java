package com.moduleBookMall.data.modle;

import android.os.Parcelable;

import com.common.base.Base;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class BookData extends Base implements Parcelable {

    public abstract List<Book> data();

    public static TypeAdapter<BookData> typeAdapter(Gson gson) {
        return new AutoValue_BookData.GsonTypeAdapter(gson);
    }
}
