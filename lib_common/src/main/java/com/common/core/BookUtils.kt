package com.common.core

import com.common.data.bean.BookNative
import com.common.utils.CommonUtils

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
object BookUtils {

    fun loadBookDetail(book: BookNative?) {
        CommonUtils.navigationPostcard(RouterHub.COMMON_BOOK_DETAILS_ACTIVITY).withParcelable("book", book).navigation()
    }
}