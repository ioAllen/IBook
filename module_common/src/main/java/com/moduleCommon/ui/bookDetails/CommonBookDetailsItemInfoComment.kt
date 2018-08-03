package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.moduleCommon.R
import kotlinx.android.synthetic.main.common_include_book_details_comment.view.*


class CommonBookDetailsItemInfoComment : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_details_comment, this, true)
    }

    /**
     * 隐藏评论提示文本框
     */
    fun hideCommentHintTv() {
        detailCommentHint.visibility = View.GONE
    }
}
