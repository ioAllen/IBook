package com.moduleBookMall.ui.classify

import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import com.moduleBookMall.data.test.TestBean
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/1.
 * QQ:619321796
 */
class BookMallClassifyAdapter @Inject
constructor() : BaseQuickAdapter<TestBean, BaseViewHolder>((R.layout.book_mall_item_classify)) {

    override fun onCreateLayoutHelper(): LayoutHelper? {
        return null
    }

    override fun convert(helper: BaseViewHolder?, item: TestBean?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.coverIv)
            if (coverIv != null) {
                IStringUtils.displayImage(mContext, item.coverPath, coverIv)
            }
            helper?.setText(R.id.bookNameTv, item.name)
        }
    }

}