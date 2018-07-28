package com.moduleBookshelf.ui.launch

import com.common.base.BaseActivity
import com.common.base.BaseApplication
import com.common.utils.StatusBarUtil
import com.moduleBookshelf.R
import com.moduleBookshelf.ui.bookshelf.BookshelfFragment

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
class BookShelfLaunchActivity : BaseActivity() {


    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_launch_activity
    }

    override fun initData() {
        StatusBarUtil.setTranslucent(this)
        BaseApplication[this].mHandler.postDelayed({
            startActivity(BookshelfFragment.createIntent(this))
            finish()
        }, 2000)
    }

}