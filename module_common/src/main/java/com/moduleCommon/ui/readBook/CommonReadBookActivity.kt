package com.moduleCommon.ui.readBook

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.moduleCommon.R
import com.moduleCommon.ui.base.CommonBaseActivity

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
@Route(path = RouterHub.COMMON_READ_BOOK_ACTIVITY)
class CommonReadBookActivity : CommonBaseActivity() {

    override fun attachLayoutRes(): Int {
        return R.layout.common_read_book_activity
    }

    override fun initData() {
    }

}