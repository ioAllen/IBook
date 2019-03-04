package com.moduleBookMall.ui.classifyList

import android.view.View
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.common.core.BookUtils
import com.common.data.bean.BookNative
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import com.moduleBookMall.data.modle.Book
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class BookMallClassifyListAdapter @Inject constructor() : BaseQuickAdapter<Book, BaseViewHolder>((R.layout.book_mall_item_recommend)) {
    override fun onCreateLayoutHelper(): LayoutHelper {
        return LinearLayoutHelper()
    }

    override fun convert(helper: BaseViewHolder?, item: Book?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.itemCoverIv)
            if (coverIv != null) {
                IStringUtils.displayImage(mContext, item.coverPath(), coverIv)
            }
            helper?.setText(R.id.itemNameTv, item.bookName())
            helper?.setText(R.id.itemAuthorTv, item.author())
            helper?.setText(R.id.itemContentTv, item.bookDescription())
            helper?.getView<View>(R.id.itemRootLayout)?.setOnClickListener {
                BookUtils.loadBookDetail(null)
            }
        }
    }
}