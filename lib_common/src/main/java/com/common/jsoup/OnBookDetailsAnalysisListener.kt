package com.common.jsoup

import com.common.data.bean.BookNative

/**
 * author：WangLei
 * date:2018/8/6.
 * QQ:619321796
 */
interface OnBookDetailsAnalysisListener {
    fun onBookAnalysisSuccess(book: BookNative)
}