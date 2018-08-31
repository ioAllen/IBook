package com.common.core

import com.common.data.bean.BookNative
import com.common.utils.CommonUtils

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
object BookUtils {

    fun loadBookDetail(bookNative: BookNative?) {
        CommonUtils.navigationPostcard(RouterHub.COMMON_BOOK_DETAILS_ACTIVITY).withParcelable("bookNative", bookNative).navigation()
    }
}