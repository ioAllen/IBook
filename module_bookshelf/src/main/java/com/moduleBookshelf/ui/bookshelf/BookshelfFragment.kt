package com.moduleBookshelf.ui.bookshelf

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.common.utils.LayoutManagerUtil
import com.moduleBookshelf.R
import com.moduleBookshelf.data.test.TestData
import com.moduleBookshelf.ui.base.BaseFragment
import kotlinx.android.synthetic.main.bookshelf_main_fragment.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKSHELF_MAIN_FRAGMENT)
class BookshelfFragment : BaseFragment() {

    @Inject
    lateinit var mAdapter: BookshelfAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.bookshelf_main_fragment
    }

    override fun injection() {
        fragmentComponent()?.inject(this)
    }

    override fun initData() {
        bookshelfRv.layoutManager = LayoutManagerUtil.getGridLayoutManager(context, 3)
        bookshelfRv.adapter = mAdapter

        mAdapter.addData(TestData.loadBookshelfData())

        mAdapter.setOnItemClickListener { adapter, view, position ->
            CommonUtils.navigation(context, RouterHub.BOOKSHELF_READ_BOOK_ACTIVITY)
        }
    }
}