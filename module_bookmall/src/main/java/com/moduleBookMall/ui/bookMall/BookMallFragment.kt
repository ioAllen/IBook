package com.moduleBookMall.ui.bookMall

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.moduleBookMall.R
import com.moduleBookMall.ui.base.BookMallBaseFragment

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKMall_MAIN_FRAGMENT)
class BookMallFragment : BookMallBaseFragment() {

    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_main_fragment
    }

    override fun initData() {
    }
}