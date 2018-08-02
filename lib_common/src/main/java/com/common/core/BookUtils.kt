package com.common.core

import com.common.utils.CommonUtils

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
object BookUtils {

    fun loadBookDetail(bookName: String) {
        CommonUtils.navigationPostcard(RouterHub.COMMON_BOOK_DETAILS_ACTIVITY).withString("bookName", bookName).navigation()
    }
}