package com.moduleBookMall.ui.classify

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.common.data.test.TestBean
import com.common.data.test.TestData
import com.common.utils.CommonUtils
import com.common.utils.LayoutManagerUtil
import com.moduleBookMall.R
import com.moduleBookMall.data.modle.Book
import com.moduleBookMall.data.modle.BookData
import com.moduleBookMall.ui.base.BookMallBaseActivity
import kotlinx.android.synthetic.main.book_mall_recycler_list.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/1.
 * QQ:619321796
 * 书城分类
 */
@Route(path = RouterHub.BOOK_MALL_CLASSIFY)
class BookMallClassifyActivity : BookMallBaseActivity() {

    @Inject
    lateinit var bookMallClassifyAdapter: BookMallClassifyAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_recycler_list
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        setTitleTxt(R.string.book_mall_menu_sort)
        showViewBreak(true)

        main_list.setBackgroundColor(Color.WHITE)
        main_list.layoutManager = LayoutManagerUtil.getGridLayoutManager(this, 3)
        main_list.adapter = bookMallClassifyAdapter

        bookMallClassifyAdapter.addData(TestData.loadTestData())

        bookMallClassifyAdapter.setOnItemClickListener { adapter, _, position ->
            val data = adapter.getItem(position) as TestBean
            CommonUtils.navigationPostcard(RouterHub.BOOK_MALL_CLASSIFY_LIST).withString("name", data.name).navigation()
        }
    }

}