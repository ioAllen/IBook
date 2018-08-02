package com.moduleBookMall.ui.bookMall

import android.view.View
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.common.core.BookUtils
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import com.moduleBookMall.data.bean.BookRecommendBean
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class BookMallItemBookAdapter @Inject
constructor() : BaseQuickAdapter<BookRecommendBean, BaseViewHolder>((R.layout.book_mall_item_book_hot)) {
    override fun convert(helper: BaseViewHolder?, item: BookRecommendBean?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.coverIv)
            if (coverIv != null) {
                IStringUtils.displayImage(mContext, item.coverPath, coverIv)
            }
            helper?.setText(R.id.bookNameTv, item.bookName)
            helper?.getView<View>(R.id.itemRootLayout)?.setOnClickListener { BookUtils.loadBookDetail(item.bookName)}
        }
    }

    override fun onCreateLayoutHelper(): LayoutHelper? {
        return GridLayoutHelper(3)
    }
}