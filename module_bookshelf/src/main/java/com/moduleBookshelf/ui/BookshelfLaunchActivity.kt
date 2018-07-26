package com.moduleBookshelf.ui

import com.common.base.BaseActivity
import com.common.base.BaseApplication
import com.moduleBookshelf.R

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
class BookshelfLaunchActivity : BaseActivity() {


    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_launch_activity
    }

    override fun initData() {
        BaseApplication[this].mHandler.postDelayed({ startActivity(BookshelfMainActivity.createIntent(this)) }, 2000)
    }

}