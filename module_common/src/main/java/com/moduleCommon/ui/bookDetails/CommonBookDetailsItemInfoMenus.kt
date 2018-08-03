package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.moduleCommon.R
import kotlinx.android.synthetic.main.common_include_book_details_menus.view.*


class CommonBookDetailsItemInfoMenus : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_details_menus, this, true)

        bookDetailShareTv.setOnClickListener {  }
        bookDetailCatalogueTv.setOnClickListener {  }
        bookDetailLikeTv.setOnClickListener {  }
    }

}
