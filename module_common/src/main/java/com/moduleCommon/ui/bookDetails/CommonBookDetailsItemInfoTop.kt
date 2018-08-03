package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.common.data.bean.BookNative
import com.common.utils.IStringUtils
import com.common.widget.TitleBar
import com.moduleCommon.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.common_include_book_detail_info_top.view.*
import net.qiujuer.genius.blur.StackBlur


class CommonBookDetailsItemInfoTop : RelativeLayout {

    private val compositeDisposable = CompositeDisposable()
    private var mTitleBar: TitleBar? = null
    private var bookNative: BookNative? = null;

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_detail_info_top, this, true)
    }

    private fun initData() {
        itemAuthorTv.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        itemAuthorTv.paint.isAntiAlias = true

        detailBackground.alpha = 0.5f

        if (bookNative != null) {
            itemNameTv.text = bookNative?.bookName
            itemAuthorTv.text = bookNative?.author

            val path = bookNative?.coverPath ?: return
            IStringUtils.displayImage(context, path, itemCoverIv)
            compositeDisposable.add(IStringUtils.displayImageBlur(context, path, detailBackground)
                    .subscribe(Consumer<Bitmap> { aData ->
                        val newBitmap = StackBlur.blurNativelyPixels(aData, 20, false)
                        detailBackground.setImageBitmap(newBitmap)
                        mTitleBar?.setTitleBarBackgroundBitmap(newBitmap)
                    }))
        }

    }

    fun setTitleBar(titleBar: TitleBar) {
        mTitleBar = titleBar
    }

    fun setData(bookNative: BookNative?) {
        this.bookNative = bookNative
        initData()
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
