package com.moduleBookshelf.ui.bookshelf

import android.content.Context
import android.content.Intent
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.moduleBookshelf.R
import com.moduleBookshelf.ui.base.BaseActivity

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
class BookshelfActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BookshelfActivity::class.java)
        }
    }

    override fun attachLayoutRes(): Int {
        return R.layout.activity_content
    }

    override fun initData() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = CommonUtils.obtainARouterFragment(RouterHub.BOOKSHELF_MAIN_FRAGMENT)
        transaction.add(R.id.contentLayout, fragment)
        transaction.commit()
    }

}