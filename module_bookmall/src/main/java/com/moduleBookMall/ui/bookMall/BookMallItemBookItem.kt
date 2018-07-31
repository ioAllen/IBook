package com.moduleBookMall.ui.bookMall

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import com.moduleBookMall.data.bean.BookRecommendBean
import kotlinx.android.synthetic.main.book_mall_item_recommend.view.*


class BookMallItemBookItem : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.book_mall_item_recommend, this, true)
    }

    fun convert(item: BookRecommendBean?) {
        if (item != null) {
            IStringUtils.displayImage(context, item.coverPath, itemCoverIv)
            itemNameTv.text = item.bookName
            itemAuthorTv.text = item.author
            itemContentTv.text = item.content
            itemRootLayout.setOnClickListener { }
        }
    }
}
