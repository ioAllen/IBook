package com.moduleCommon.ui.bookDetails

import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.common.core.RouterHub
import com.common.data.bean.BookNative
import com.common.utils.CommonUtils
import com.common.utils.DelegateAdapterUtil
import com.common.utils.StatusBarUtil
import com.moduleCommon.R
import com.moduleCommon.data.test.TestData
import com.moduleCommon.ui.base.CommonBaseActivity
import kotlinx.android.synthetic.main.common_book_details_activity.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 * 书籍详情
 */
@Route(path = RouterHub.COMMON_BOOK_DETAILS_ACTIVITY)
class CommonBookDetailsActivity : CommonBaseActivity() {

    @Autowired
    @JvmField
    var bookNative: BookNative? = null

    private lateinit var mDelegateAdapter: DelegateAdapter

    private lateinit var commonBookDetailsItemInfoTop: CommonBookDetailsItemInfoTop
    private lateinit var commonBookDetailsItemInfoMenus: CommonBookDetailsItemInfoMenus
    private lateinit var commonBookDetailsItemInfo: CommonBookDetailsItemInfo
    private lateinit var commonBookDetailsItemInfoComment: CommonBookDetailsItemInfoComment

    @Inject
    lateinit var commonBookCommentAdapter: CommonBookCommentAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.common_book_details_activity
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        StatusBarUtil.setTransparentForWindow(this)
        mTitleBar.setTitle(bookNative?.bookName)
        mTitleBar.showViewBreak(true)
        mTitleBar.alpha = 0f
        mTitleBar.getBackgroundImageView().alpha = 0f
        actionBarUtils.addTitleBarStatusBarHeight(mTitleBar)

        commonBookDetailsItemInfoTop = CommonBookDetailsItemInfoTop(this)
        commonBookDetailsItemInfoMenus = CommonBookDetailsItemInfoMenus(this)
        commonBookDetailsItemInfo = CommonBookDetailsItemInfo(this)
        commonBookDetailsItemInfoComment = CommonBookDetailsItemInfoComment(this)
        commonBookDetailsItemInfoTop.setTitleBar(mTitleBar)
        commonBookDetailsItemInfoTop.setData(bookNative)
        commonBookDetailsItemInfo.setData(bookNative)
        commonBookDetailsItemInfoMenus.setData(bookNative)

        val layoutManager = VirtualLayoutManager(this)
        main_list.layoutManager = layoutManager

        val viewPool = RecyclerView.RecycledViewPool()
        main_list.recycledViewPool = viewPool
        viewPool.setMaxRecycledViews(0, 10)
        mDelegateAdapter = DelegateAdapter(layoutManager)
        main_list.adapter = mDelegateAdapter

        val adapters = DelegateAdapterUtil.getAdapterList()

        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoTop))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoMenus))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfo))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoComment))

        val commentList = TestData.loadCommentData()
        val count = (Math.random() * 10).toInt()
        if (count > 0) {
            commonBookCommentAdapter.addData(commentList.subList(0, count))
            commonBookDetailsItemInfoComment.hideCommentHintTv()
            adapters.add(commonBookCommentAdapter)
        }

        mDelegateAdapter.setAdapters(adapters)

        main_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topHeight = resources.getDimension(R.dimen.x600)
                val firstPosition = layoutManager.findFirstVisibleItemPosition()
                val offset = main_list.computeVerticalScrollOffset()
                if (offset >= topHeight) {
                    mTitleBar.alpha = 1f;
                    mTitleBar.getBackgroundImageView().alpha = 1f
                } else if (firstPosition == 0) {
                    val alpha = offset / topHeight
                    mTitleBar.alpha = alpha
                    mTitleBar.getBackgroundImageView().alpha = alpha
                }
            }
        })

        bookDetailsReadBt.setOnClickListener {
            if (bookNative != null && bookNative!!.bookCatalogue != null) {
                val category = bookNative!!.bookCatalogue?.get(0)
                CommonUtils.navigationPostcard(RouterHub.COMMON_WEB_VIEW_ACTIVITY).withString("title", category?.title).withString("url", category?.path).navigation()
            }
        }
    }

    override fun noStatusBar(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        commonBookDetailsItemInfoTop.onDestroy()
    }
}