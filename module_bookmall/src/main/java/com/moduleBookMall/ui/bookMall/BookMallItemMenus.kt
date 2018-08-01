package com.moduleBookMall.ui.bookMall

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.moduleBookMall.R
import kotlinx.android.synthetic.main.book_mall_menus.view.*


class BookMallItemMenus : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.book_mall_menus, this, true)
        initEvents()
    }

    private fun initEvents() {
        mallMenusBut1.setOnClickListener { CommonUtils.navigation(context, RouterHub.BOOK_MALL_CLASSIFY) }
        mallMenusBut2.setOnClickListener { }
        mallMenusBut3.setOnClickListener { }
        mallMenusBut4.setOnClickListener { CommonUtils.navigation(context, RouterHub.BOOK_MALL_FIND_ACTIVITY) }
    }

}
