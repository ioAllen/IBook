package com.moduleBookshelf.ui

import android.content.Context
import android.content.Intent
import com.common.base.BaseActivity
import com.moduleBookshelf.R

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
class BookshelfMainActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BookshelfMainActivity::class.java)
        }
    }

    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_main_activity
    }

    override fun initData() {
    }
}