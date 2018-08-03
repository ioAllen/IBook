package com.moduleBookshelf.ui.bookshelf

import com.alibaba.android.arouter.facade.annotation.Route
import com.common.core.BookUtils
import com.common.core.RouterHub
import com.common.data.bean.BookNative
import com.common.data.test.TestData
import com.common.utils.LayoutManagerUtil
import com.moduleBookshelf.R
import com.moduleBookshelf.ui.base.BookshelfBaseFragment
import kotlinx.android.synthetic.main.bookshelf_main_fragment.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/26.
 * QQ:619321796
 */
@Route(path = RouterHub.BOOKSHELF_MAIN_FRAGMENT)
class BookshelfFragment : BookshelfBaseFragment() {

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

        mAdapter.addData(TestData.loadBookData())

        mAdapter.setOnItemClickListener { adapter, _, position ->
            val data = adapter.getItem(position) as BookNative
            BookUtils.loadBookDetail(data)
        }
    }
}