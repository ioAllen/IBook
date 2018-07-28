package com.moduleBookshelf.ui.bookshelf

import com.common.base.BaseActivity
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.moduleBookshelf.R

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
class BookshelfActivity : BaseActivity() {
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