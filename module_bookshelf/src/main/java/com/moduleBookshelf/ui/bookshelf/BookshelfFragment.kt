package com.moduleBookshelf.ui.bookshelf

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseFragment
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.moduleBookshelf.R
import kotlinx.android.synthetic.main.bookshelf_main_fragment.*

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKSHELF_MAIN_FRAGMENT)
class BookshelfFragment : BaseFragment() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BookshelfFragment::class.java)
        }
    }

    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_main_fragment
    }

    override fun initData() {
        testBut.setOnClickListener { CommonUtils.navigation(context,RouterHub.BOOKSHELF_READ_BOOK_ACTIVITY) }
    }
}