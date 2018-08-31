package com.moduleBookMall.data.modle;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import javax.annotation.Nullable;

@AutoValue
public abstract class Book  implements Parcelable {
    public abstract int id();
    public abstract String bookName();
    public abstract String coverPath();
    public abstract String bookDescription();
    public abstract String author();
    @Nullable
    public abstract String bookTag();
    public abstract String bookType();
    public abstract String newsUpdateContent();
    public abstract String newsUpdateDate();
    public abstract int clickCount();
    public abstract int likeCount();
    public abstract int commentCount();
    public abstract int readCount();
    public abstract int removeCount();
    public abstract int searchCount();
    public abstract int grade();
    public abstract int words();
    public abstract String state();
    public abstract String bookLinkPath();
    public abstract String createDate();

    public static TypeAdapter<Book> typeAdapter(Gson gson) {
        return new AutoValue_Book.GsonTypeAdapter(gson);
    }
}