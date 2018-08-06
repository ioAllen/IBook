package com.common.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 * 本地书本对象
 */
class BookNative() : Parcelable {

    var uid: String? = null

    /**
     * 书本名称
     */
    var bookName: String? = null

    /**
     * 封面
     */
    var coverPath: String? = null

    /**
     * 书本描述
     */
    var bookDescription: String? = null

    /**
     * 作者
     */
    var author: String? = null

    /**
     * 书本标签
     */
    var bookTag: String? = null

    /**
     * 书本类型
     */
    var bookType: String? = null

    /**
     * 最新更新章节内容
     */
    var newsUpdateContent: String? = null

    /**
     * 最新更新章节时间
     */
    var newsUpdateDate: Long = 0

    /**
     * 点击数量
     */
    var clickCount: Int = 0

    /**
     * 喜欢数量
     */
    var likeCount: Int = 0

    /**
     * 书本评分
     */
    var grade: Float = 0f

    /**
     * 数据源（链接）
     */
    var bookLinkPath: ArrayList<String>? = null

    /**
     * 目录
     */
    var bookCatalogue: ArrayList<BookCatalogue>? = null

    constructor(parcel: Parcel) : this() {
        uid = parcel.readString()
        bookName = parcel.readString()
        coverPath = parcel.readString()
        bookDescription = parcel.readString()
        author = parcel.readString()
        bookTag = parcel.readString()
        bookType = parcel.readString()
        newsUpdateContent = parcel.readString()
        newsUpdateDate = parcel.readLong()
        clickCount = parcel.readInt()
        likeCount = parcel.readInt()
        grade = parcel.readFloat()
        bookLinkPath = parcel.readArrayList(String::class.java.classLoader) as ArrayList<String>?
        bookCatalogue = parcel.readArrayList(BookCatalogue::class.java.classLoader) as ArrayList<BookCatalogue>?
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(bookName)
        parcel.writeString(coverPath)
        parcel.writeString(bookDescription)
        parcel.writeString(author)
        parcel.writeString(bookTag)
        parcel.writeString(bookType)
        parcel.writeString(newsUpdateContent)
        parcel.writeLong(newsUpdateDate)
        parcel.writeInt(clickCount)
        parcel.writeInt(likeCount)
        parcel.writeFloat(grade)
        parcel.writeList(bookLinkPath)
        parcel.writeList(bookCatalogue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookNative> {
        override fun createFromParcel(parcel: Parcel): BookNative {
            return BookNative(parcel)
        }

        override fun newArray(size: Int): Array<BookNative?> {
            return arrayOfNulls(size)
        }
    }


}
