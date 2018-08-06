package com.moduleCommon.ui.bookDetails

import com.alibaba.android.vlayout.LayoutHelper
import com.common.data.bean.BookCatalogue
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.moduleCommon.R
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
class CommonBookDirectoryAdapter @Inject
constructor() : BaseQuickAdapter<BookCatalogue, BaseViewHolder>((R.layout.common_item_book_directory)) {
    override fun onCreateLayoutHelper(): LayoutHelper? {
        return null
    }

    override fun convert(helper: BaseViewHolder?, item: BookCatalogue?) {
        if (helper != null && item != null) {
            helper.setText(R.id.itemTxtTv, item.title)
        }
    }

}