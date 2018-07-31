package com.moduleBookMall.ui.bookMall

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.common.core.RouterHub
import com.moduleBookMall.R
import com.moduleBookMall.data.test.TestData
import com.moduleBookMall.ui.base.BookMallBaseFragment
import com.moduleBookMall.utils.DelegateAdapterUtil
import kotlinx.android.synthetic.main.book_mall_main_fragment.*
import javax.inject.Inject


/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKMall_MAIN_FRAGMENT)
class BookMallFragment : BookMallBaseFragment() {

    private var mDelegateAdapter: DelegateAdapter? = null
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
     * 单个书籍view
     */
    private lateinit var bookMallItemBookItem: BookMallItemBookItem

    @Inject
    lateinit var bookMallItemRecommendAdapter: BookMallItemRecommendAdapter
    @Inject
    lateinit var bookMallItemBookAdapter: BookMallItemBookAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.book_mall_main_fragment
    }

    override fun injection() {
        fragmentComponent()?.inject(this)
    }

    override fun initData() {
        bookMallItemBanner = BookMallItemBanner(context)
        bookMallItemMenus = BookMallItemMenus(context)
        bookMallItemContentTitleTopRecommend = BookMallItemContentTitle(context)
        bookMallItemContentTitleHotRecommend = BookMallItemContentTitle(context)
        bookMallItemBookItem = BookMallItemBookItem(context)

        val layoutManager = VirtualLayoutManager(context!!)
        main_list.layoutManager = layoutManager

        val viewPool = RecyclerView.RecycledViewPool()
        main_list.recycledViewPool = viewPool
        viewPool.setMaxRecycledViews(0, 10)
        mDelegateAdapter = DelegateAdapter(layoutManager)
        main_list.adapter = mDelegateAdapter

        bookMallItemBanner.setData(TestData.loadBannerData())

        //填充banner
        val adapters = DelegateAdapterUtil.getAdapterList()
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemBanner))

        //填充菜单
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemMenus))

        //填充高分神作标题
        bookMallItemContentTitleTopRecommend.setItemTitle(R.string.book_mall_high_mark)
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemContentTitleTopRecommend))

        //填充高分神作内容
        bookMallItemRecommendAdapter.addData(TestData.loadBookshelfData())
        adapters.add(bookMallItemRecommendAdapter)


        //填充热门推荐标题
        bookMallItemContentTitleHotRecommend.setItemTitle(R.string.book_mall_recommend)
        bookMallItemContentTitleHotRecommend.setChangeListener(View.OnClickListener { showToast("换一批") })
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemContentTitleHotRecommend))


        //填充热门推荐内容
        val hotList = TestData.loadBookshelfData()

        bookMallItemBookItem.convert(hotList[0])
        adapters.add(DelegateAdapter.simpleAdapter(bookMallItemBookItem))

        bookMallItemBookAdapter.addData(hotList.subList(1, hotList.size))
        adapters.add(bookMallItemBookAdapter)

        mDelegateAdapter?.setAdapters(adapters)
    }
}