package com.moduleBookMall.ui.find

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.BookUtils
import com.common.core.RouterHub
import com.common.data.bean.BookNative
import com.common.jsoup.JsoupBookDetailsManager
import com.common.jsoup.JsoupNewsUpdateManager
import com.common.jsoup.OnBookDataAnalysisListener
import com.common.jsoup.OnBookDetailsAnalysisListener
import com.common.utils.CommonConstant
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

    @Inject
    lateinit var jsoupNewsUpdateManager: JsoupNewsUpdateManager

    @Inject
    lateinit var jsoupBookDetailsManager: JsoupBookDetailsManager

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


        jsoupNewsUpdateManager.loadData(CommonConstant.biquge)
        jsoupNewsUpdateManager.setOnBookDataAnalysisListener(object : OnBookDataAnalysisListener {
            override fun onBookAnalysisSuccess(list: List<BookNative>) {
                bookMallFindNewsAdapter.addData(list)
            }
        })
        bookMallFindNewsAdapter.setOnItemClickListener { adapter, _, position ->
            val book = adapter.getItem(position) as BookNative
            jsoupBookDetailsManager.loadBookCatalogue(book)
            showProgressDialog(R.string.brvah_loading, true)
        }
        jsoupBookDetailsManager.setOnBookDetailsAnalysisListener(object : OnBookDetailsAnalysisListener {
            override fun onBookAnalysisSuccess(book: BookNative) {
                dismissProgressDialog()
                BookUtils.loadBookDetail(book)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        jsoupNewsUpdateManager.onDestroy()
    }

}