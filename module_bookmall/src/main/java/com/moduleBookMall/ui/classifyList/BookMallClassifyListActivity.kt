package com.moduleBookMall.ui.classifyList

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.common.core.RouterHub
import com.moduleBookMall.data.test.TestData
import com.common.utils.DelegateAdapterUtil
import com.moduleBookMall.R
import com.moduleBookMall.ui.base.BookMallBaseActivity
import com.moduleBookMall.ui.bookMall.BookMallItemRecommendAdapter
import kotlinx.android.synthetic.main.book_mall_classify_list.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/1.
 * QQ:619321796
 * 分类列表
 */
@Route(path = RouterHub.BOOK_MALL_CLASSIFY_LIST)
class BookMallClassifyListActivity : BookMallBaseActivity() {

    @Autowired
    @JvmField
    var name: String = ""

    private lateinit var mDelegateAdapter: DelegateAdapter

    private lateinit var bookMallItemClassifyFilter: BookMallItemClassifyFilter

    @Inject
    lateinit var bookMallItemRecommendAdapter: BookMallItemRecommendAdapter


    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_classify_list
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        setTitleTxt(name)
        showViewBreak(true)

        bookMallItemClassifyFilter = BookMallItemClassifyFilter(this)


        val layoutManager = VirtualLayoutManager(this)
        main_list.layoutManager = layoutManager

        val viewPool = RecyclerView.RecycledViewPool()
        main_list.recycledViewPool = viewPool
        viewPool.setMaxRecycledViews(0, 10)

        mDelegateAdapter = DelegateAdapter(layoutManager)
        main_list.adapter = mDelegateAdapter

        //填充筛选view
        val adapters = DelegateAdapterUtil.getAdapterList()
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemClassifyFilter))

        bookMallItemRecommendAdapter.addData(TestData.loadBookshelfData())
        adapters.add(bookMallItemRecommendAdapter)

        mDelegateAdapter.setAdapters(adapters)

        initEvents(layoutManager)
    }

    private fun initEvents(layoutManager: VirtualLayoutManager) {
        main_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val first = layoutManager.findFirstVisibleItemPosition()
                if (first == 0) {
                    hintToTopView()
                } else {
                    if (dy < 0) {
                        showToTopView()
                    } else if (dy > 0) {
                        hintToTopView()
                    }
                }
            }
        })

        releasePosts.setOnClickListener { main_list.smoothScrollToPosition(0); }
    }

    private fun showToTopView() {
        if (!releasePosts.isShown) {
            releasePosts.visibility = View.VISIBLE
        }
    }

    private fun hintToTopView() {
        if (releasePosts.isShown) {
            releasePosts.visibility = View.INVISIBLE
        }
    }
}