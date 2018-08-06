package com.moduleBookMall.ui.find

import android.app.Activity
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.common.data.bean.BookNative
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import com.common.jsoup.JsoupBookDetailsManager
import com.common.jsoup.OnBookDetailsAnalysisListener
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class BookMallFindNewsAdapter @Inject constructor() : BaseQuickAdapter<BookNative, BaseViewHolder>((R.layout.book_mall_item_find_news)) {
    override fun onCreateLayoutHelper(): LayoutHelper {
        return LinearLayoutHelper()
    }

    override fun convert(helper: BaseViewHolder?, item: BookNative?) {
        if (item != null) {
            val coverIv = helper?.getView<ImageView>(R.id.itemCoverIv)
            val coverPath = item.coverPath
            if (IStringUtils.isNullOrEmpty(coverPath)) {
                val detailsAnalysis = JsoupBookDetailsManager()
                detailsAnalysis.loadData(item)
                detailsAnalysis.setOnBookDetailsAnalysisListener(object : OnBookDetailsAnalysisListener {
                    override fun onBookAnalysisSuccess(book: BookNative) {
                        displayImage(coverIv, book.coverPath)
                        detailsAnalysis.onDestroy()
                    }
                })
            }
            displayImage(coverIv, coverPath)
            helper?.setText(R.id.itemNameTv, item.bookName)
            helper?.setText(R.id.itemAuthorTv, item.author)
            helper?.setText(R.id.itemContentTv, item.newsUpdateContent)
        }
    }

    private fun displayImage(coverIv: ImageView?, coverPath: String?) {
        if (mContext is Activity) {
            val a = mContext as Activity
            if (!a.isFinishing && coverIv != null) {
                IStringUtils.displayImage(mContext, coverPath, coverIv)
            }
        }
    }
}