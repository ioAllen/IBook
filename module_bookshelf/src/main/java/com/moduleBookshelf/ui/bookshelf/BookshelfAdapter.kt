package com.moduleBookshelf.ui.bookshelf

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookshelf.R
import com.moduleBookshelf.data.bean.BookshelfBean
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class BookshelfAdapter @Inject
constructor() : BaseQuickAdapter<BookshelfBean, BaseViewHolder>((R.layout.bookshelf_item_bookshelf)) {
    override fun convert(helper: BaseViewHolder?, item: BookshelfBean?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.coverIv)
            if (coverIv != null) {
                IStringUtils.displayImage(mContext, item.bookPath, coverIv)
            }
            helper?.setText(R.id.bookNameTv, item.bookName)
        }
    }
}