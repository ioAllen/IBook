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
    var bookCatalogue: ArrayList<String>? = null

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
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(uid)
        dest?.writeString(bookName)
        dest?.writeString(coverPath)
        dest?.writeString(bookDescription)
        dest?.writeString(author)
        dest?.writeString(bookTag)
        dest?.writeString(bookType)
        dest?.writeString(newsUpdateContent)
        dest?.writeLong(newsUpdateDate)
        dest?.writeInt(clickCount)
        dest?.writeInt(likeCount)
        dest?.writeFloat(grade)
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
