package com.moduleBookMall.ui.classifyList

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.moduleBookMall.R
import kotlinx.android.synthetic.main.book_mall_classify_filter.view.*


class BookMallItemClassifyFilter : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.book_mall_classify_filter, this, true)
        initEvents()
    }

    private fun initEvents() {
        setViewSelected(filterHotLL)
        filterTypeTv1.isSelected = true
        filterHotLL.setOnClickListener {
            setViewSelected(filterHotLL)
            setViewSelectedNo(filterTopLL)
        }
        filterTopLL.setOnClickListener {
            setViewSelectedNo(filterHotLL)
            setViewSelected(filterTopLL)
        }
        filterTypeTv1.setOnClickListener { v ->
            setTypeSelectedNo()
            v.isSelected = true
        }
        filterTypeTv2.setOnClickListener { v ->
            setTypeSelectedNo()
            v.isSelected = true
        }
        filterTypeTv3.setOnClickListener { v ->
            setTypeSelectedNo()
            v.isSelected = true
        }
    }

    private fun setTypeSelectedNo() {
        filterTypeTv1.isSelected = false
        filterTypeTv2.isSelected = false
        filterTypeTv3.isSelected = false
    }

    private fun setViewSelected(linearLayout: LinearLayout) {
        linearLayout.isSelected = true
        linearLayout.getChildAt(1).visibility = View.VISIBLE
    }

    private fun setViewSelectedNo(linearLayout: LinearLayout) {
        linearLayout.isSelected = false
        linearLayout.getChildAt(1).visibility = View.INVISIBLE
    }

}
