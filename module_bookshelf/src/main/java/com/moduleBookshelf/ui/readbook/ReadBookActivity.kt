package com.moduleBookshelf.ui.readbook

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.moduleBookshelf.R
import com.moduleBookshelf.ui.base.BaseActivity

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKSHELF_READ_BOOK_ACTIVITY)
class ReadBookActivity : BaseActivity() {

    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_read_book_activity
    }

    override fun initData() {
    }

}