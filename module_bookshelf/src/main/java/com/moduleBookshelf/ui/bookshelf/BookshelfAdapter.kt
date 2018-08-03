package com.moduleBookshelf.ui.bookshelf

import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.common.data.bean.BookNative
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookshelf.R
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class BookshelfAdapter @Inject
constructor() : BaseQuickAdapter<BookNative, BaseViewHolder>((R.layout.bookshelf_item_bookshelf)) {
    override fun convert(helper: BaseViewHolder?, item: BookNative?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.coverIv)
            if (coverIv != null) {
                IStringUtils.displayImage(mContext, item.coverPath, coverIv)
            }
            helper?.setText(R.id.bookNameTv, item.bookName)
        }
    }

    override fun onCreateLayoutHelper(): LayoutHelper? {
        return null
    }
}