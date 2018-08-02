package com.moduleCommon.ui.launch

import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.common.utils.LayoutManagerUtil
import com.moduleCommon.R
import com.moduleCommon.ui.base.CommonBaseActivity
import kotlinx.android.synthetic.main.common_recycler_list.*
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
class CommonLaunchActivity : CommonBaseActivity() {

    @Inject
    lateinit var mAdapter: CommonLaunchAdapter

    override fun attachLayoutRes(): Int {
        return R.layout.common_recycler_list
    }

    override fun injection() {
        activityComponent()?.inject(this)
    }

    override fun initData() {
        main_list.layoutManager = LayoutManagerUtil.getVerticalLinearLayoutManager(this)
        main_list.adapter = mAdapter
        val menus = ArrayList<String>()
        menus.add("书本详情")
        menus.add("个人")
        mAdapter.addData(menus)

        mAdapter.setOnItemClickListener { adapter, _, position ->
            val item = adapter.getItem(position).toString()
            if (item == "书本详情") {
                CommonUtils.navigationPostcard(RouterHub.COMMON_BOOK_DETAILS_ACTIVITY).withString("bookName", item).navigation()
            }
        }
    }

}