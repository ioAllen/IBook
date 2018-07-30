package com.moduleBookMall.ui.bookMall

import android.content.Context
import android.content.Intent
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.moduleBookMall.R
import com.moduleBookMall.ui.base.BookMallBaseActivity

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
class BookMallActivity : BookMallBaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BookMallActivity::class.java)
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