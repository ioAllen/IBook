package com.moduleBookMall.ui.bookMall

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.common.core.RouterHub
import com.common.utils.DelegateAdapterUtil
import com.moduleBookMall.R
import com.moduleBookMall.data.test.TestData
import com.moduleBookMall.ui.base.BookMallBaseFragment
import kotlinx.android.synthetic.main.book_mall_recycler_list.*
import javax.inject.Inject


/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 * 书城界面
 */
@Route(path = RouterHub.BOOK_MALL_MAIN_FRAGMENT)
class BookMallFragment : BookMallBaseFragment() {

    private lateinit var mDelegateAdapter: DelegateAdapter
    /**
     * banner
     */
    private lateinit var bookMallItemBanner: BookMallItemBanner

    /**
     * 菜单栏
     */
    private lateinit var bookMallItemMenus: BookMallItemMenus
    /**
     * 高分神作标题
     */
    private lateinit var bookMallItemContentTitleTopRecommend: BookMallItemContentTitle

    /**
     * 热门推荐标题
     */
    private lateinit var bookMallItemContentTitleHotRecommend: BookMallItemContentTitle

    /**
     * 完本推荐标题
     */
    private lateinit var bookMallItemContentTitleOverRecommend: BookMallItemContentTitle

    /**
     * 单个书籍view
     */
    private lateinit var bookMallItemBookItem: BookMallItemBookItem

    @Inject
    lateinit var bookMallItemRecommendAdapter: BookMallItemRecommendAdapter
    @Inject
    lateinit var bookMallItemBookAdapter: BookMallItemBookAdapter

    @Inject
    lateinit var bookMallItemOverBookAdapter: BookMallItemBookAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_recycler_list
    }

    override fun injection() {
        fragmentComponent()?.inject(this)
    }

    override fun initData() {
        bookMallItemBanner = BookMallItemBanner(context)
        bookMallItemMenus = BookMallItemMenus(context)
        bookMallItemContentTitleTopRecommend = BookMallItemContentTitle(context)
        bookMallItemContentTitleHotRecommend = BookMallItemContentTitle(context)
        bookMallItemContentTitleOverRecommend = BookMallItemContentTitle(context)
        bookMallItemBookItem = BookMallItemBookItem(context)

        val layoutManager = VirtualLayoutManager(context!!)
        main_list.layoutManager = layoutManager

        val viewPool = RecyclerView.RecycledViewPool()
        main_list.recycledViewPool = viewPool
        viewPool.setMaxRecycledViews(0, 10)
        mDelegateAdapter = DelegateAdapter(layoutManager)
        main_list.adapter = mDelegateAdapter

        bookMallItemBanner.setData(TestData.loadBannerData())

        val bookList = TestData.loadBookshelfData()

        //填充banner
        val adapters = DelegateAdapterUtil.getAdapterList()
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemBanner))

        //填充菜单
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemMenus))

        //填充高分神作标题
        bookMallItemContentTitleTopRecommend.setItemTitle(R.string.book_mall_high_mark)
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemContentTitleTopRecommend))

        //填充高分神作内容
        bookMallItemRecommendAdapter.addData(bookList.subList(0, 3))
        adapters.add(bookMallItemRecommendAdapter)

        //填充热门推荐标题
        bookMallItemContentTitleHotRecommend.setItemTitle(R.string.book_mall_recommend)
        bookMallItemContentTitleHotRecommend.setChangeListener(View.OnClickListener { showToast("换一批") })
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemContentTitleHotRecommend))

        //填充单个热门书籍
        bookMallItemBookItem.convert(bookList[0])
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemBookItem))

        //填充3个热门推荐
        bookMallItemBookAdapter.addData(bookList.subList(1, 4))
        adapters.add(bookMallItemBookAdapter)

        //填充完本推荐标题
        bookMallItemContentTitleOverRecommend.setItemTitle(R.string.book_mall_over_recommend)
        bookMallItemContentTitleOverRecommend.setChangeListener(View.OnClickListener { showToast("换一批") })
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemContentTitleOverRecommend))

        //填充6个完本推荐
        bookMallItemOverBookAdapter.addData(bookList)
        adapters.add(bookMallItemOverBookAdapter)

        mDelegateAdapter.setAdapters(adapters)
    }
}