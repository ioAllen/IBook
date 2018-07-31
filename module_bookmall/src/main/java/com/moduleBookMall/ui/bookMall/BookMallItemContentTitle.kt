package com.moduleBookMall.ui.bookMall

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.moduleBookMall.R
import kotlinx.android.synthetic.main.book_mall_recommend.view.*


class BookMallItemContentTitle : RelativeLayout {


    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.book_mall_recommend, this, true)
    }

    fun setItemTitle(value: String) {
        recommendTitleTv.text = value
    }

    fun setItemTitle(value: Int) {
        setItemTitle(context.getString(value))
    }

    fun setChangeListener(onClickListener: OnClickListener) {
        recommendChangeTv.visibility = View.VISIBLE
        recommendChangeTv.setOnClickListener(onClickListener)
    }

}
