package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.common.core.RouterHub
import com.common.data.bean.BookNative
import com.common.utils.CommonUtils
import com.common.utils.DialogHelper
import com.common.utils.IStringUtils
import com.common.utils.LayoutManagerUtil
import com.moduleCommon.R
import kotlinx.android.synthetic.main.common_include_book_details_menus.view.*


class CommonBookDetailsItemInfoMenus : RelativeLayout {

    private var bookNative: BookNative? = null;
    private var commonBookDirectoryAdapter = CommonBookDirectoryAdapter()

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_details_menus, this, true)

        bookDetailShareTv.setOnClickListener { }
        bookDetailCatalogueTv.setOnClickListener { showBookCatalogueDialog() }
        bookDetailLikeTv.setOnClickListener { }
    }

    fun setData(bookNative: BookNative?) {
        this.bookNative = bookNative
    }

    private fun showBookCatalogueDialog() {
        val recyclerView = RecyclerView(context)
        val layoutManager = LayoutManagerUtil.getVerticalLinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = commonBookDirectoryAdapter
        recyclerView.scrollBarStyle = RecyclerView.SCROLLBARS_INSIDE_OVERLAY
        val bookCatalogue = bookNative!!.bookCatalogue
        if (bookNative != null && bookCatalogue != null) {
            commonBookDirectoryAdapter.addData(bookCatalogue)
            var title = bookNative!!.bookName
            if (IStringUtils.isNullOrEmpty(title)) {
                title = ""
            }
            val dialog = DialogHelper.showCustomViewDialog(context, title!!, "", "", recyclerView, MaterialDialog.SingleButtonCallback { _, _ ->
            })
            commonBookDirectoryAdapter.setOnItemClickListener { _, _, position ->
                dialog.dismiss()
                val category = bookCatalogue[position]
                CommonUtils.navigationPostcard(RouterHub.COMMON_WEB_VIEW_ACTIVITY).withString("title", category.title).withString("url", category.path).navigation()
            }
        }
    }
}
