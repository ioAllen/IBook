package com.moduleCommon.ui.launch

import com.alibaba.android.vlayout.LayoutHelper
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.moduleCommon.R
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 */
class CommonLaunchAdapter @Inject
constructor() : BaseQuickAdapter<String, BaseViewHolder>(R.layout.common_item_launch) {
    override fun onCreateLayoutHelper(): LayoutHelper? {
        return null
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
        if (item != null) {
            helper?.setText(R.id.itemLaunchTv, item)
        }
    }

}