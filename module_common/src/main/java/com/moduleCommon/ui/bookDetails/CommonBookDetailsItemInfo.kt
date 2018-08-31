package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.common.data.bean.BookNative
import com.common.utils.IStringUtils
import com.moduleCommon.R
import kotlinx.android.synthetic.main.common_include_book_detail_info.view.*


class CommonBookDetailsItemInfo : RelativeLayout {

    private var bookNative: BookNative? = null

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_detail_info, this, true)
    }

    private fun initData() {
        val bookDescription = bookNative?.bookDescription
        if (!IStringUtils.isNullOrEmpty(bookDescription)) {
            detailDescriptionTv.text = Html.fromHtml(bookDescription)
        }
    }

    fun setData(bookNative: BookNative?) {
        this.bookNative = bookNative
        initData()
    }
}
