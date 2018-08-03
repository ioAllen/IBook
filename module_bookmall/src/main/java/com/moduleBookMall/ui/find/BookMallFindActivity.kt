package com.moduleBookMall.ui.find

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.common.data.test.TestData
import com.common.utils.LayoutManagerUtil
import com.moduleBookMall.R
import com.moduleBookMall.ui.base.BookMallBaseActivity
import kotlinx.android.synthetic.main.book_mall_recycler_list.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/1.
 * QQ:619321796
 * 发现、最新更新
 */
@Route(path = RouterHub.BOOK_MALL_FIND_ACTIVITY)
class BookMallFindActivity : BookMallBaseActivity() {

    @Inject
    lateinit var bookMallFindNewsAdapter: BookMallFindNewsAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_recycler_list
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        setTitleTxt(R.string.book_mall_news_update)
        showViewBreak(true)

        main_list.layoutManager = LayoutManagerUtil.getVerticalLinearLayoutManager(this)
        main_list.adapter = bookMallFindNewsAdapter

        bookMallFindNewsAdapter.addData(TestData.loadBookData())
    }

}