package com.moduleBookMall.ui.launch

import com.common.base.BaseActivity
import com.common.base.BaseApplication
import com.common.utils.StatusBarUtil
import com.moduleBookMall.R
import com.moduleBookMall.ui.bookMall.BookMallActivity

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
class BookMallLaunchActivity : BaseActivity() {


    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_launch_activity
    }

    override fun noStatusBar(): Boolean {
        return false
    }

    override fun initData() {
        StatusBarUtil.setTranslucent(this, 30)
        BaseApplication[this].mHandler.postDelayed({
            startActivity(BookMallActivity.createIntent(this))
            finish()
        }, 500)
    }

}