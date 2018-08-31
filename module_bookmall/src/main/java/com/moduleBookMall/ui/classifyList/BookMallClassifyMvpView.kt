package com.moduleBookMall.ui.classifyList

import com.common.base.MvpView
import com.moduleBookMall.data.modle.Book
import com.moduleBookMall.data.modle.BookData

interface BookMallClassifyMvpView : MvpView {

    fun loadBookByTypeSuccess(book: BookData)

}