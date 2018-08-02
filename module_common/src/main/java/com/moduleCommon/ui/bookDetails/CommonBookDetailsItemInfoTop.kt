package com.moduleCommon.ui.bookDetails

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.common.utils.IStringUtils
import com.common.widget.TitleBar
import com.moduleCommon.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.common_include_book_detail_info_top.view.*
import net.qiujuer.genius.blur.StackBlur


class CommonBookDetailsItemInfoTop : RelativeLayout {

    private val compositeDisposable = CompositeDisposable()
    var mTitleBar: TitleBar? = null

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.common_include_book_detail_info_top, this, true)
        initData()
    }

    private fun initData() {
        itemAuthorTv.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        itemAuthorTv.paint.isAntiAlias = true

        itemContentTv.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        itemContentTv.paint.isAntiAlias = true

        val path = "https://static.kuaiyankanshu.net/public/cover/80/e3/7e/80e37eebd8292793873fb830e435a3a2.jpg"
        IStringUtils.displayImage(context, path, itemCoverIv)
        compositeDisposable.add(IStringUtils.displayImageBlur(context, path, detailBackground)
                .subscribe(Consumer<Bitmap> { aData ->
                    val newBitmap = StackBlur.blurNativelyPixels(aData, 20, false)
                    detailBackground.setImageBitmap(newBitmap)
                    mTitleBar?.setTitleBarBackgroundBitmap(newBitmap)
                }))
        detailBackground.alpha = 0.3f
    }

    fun setTitleBar(titleBar: TitleBar) {
        mTitleBar = titleBar
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
