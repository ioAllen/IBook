package com.common.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * author：WangLei
 * date:2018/8/6.
 * QQ:619321796
 */
class BookCatalogue(var title: String, var path: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookCatalogue> {
        override fun createFromParcel(parcel: Parcel): BookCatalogue {
            return BookCatalogue(parcel)
        }

        override fun newArray(size: Int): Array<BookCatalogue?> {
            return arrayOfNulls(size)
        }
    }

}