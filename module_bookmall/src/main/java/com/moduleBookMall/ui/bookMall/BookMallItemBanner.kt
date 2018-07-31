package com.moduleBookMall.ui.bookMall

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import cn.bingoogolapple.bgabanner.BGABanner
import com.common.utils.IStringUtils
import com.moduleBookMall.R
import kotlinx.android.synthetic.main.book_mall_item_banner.view.*


class BookMallItemBanner : RelativeLayout, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.book_mall_item_banner, this, true)
        main_banner.setDelegate(this)
    }

    fun setData(dataList: List<String>) {
        /*
         * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
         * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
         */
        main_banner.setAutoPlayAble(dataList.size > 1)

        main_banner.setAdapter(this)
        main_banner.setData(dataList, null)
    }

    override fun fillBannerItem(bgaBanner: BGABanner, imageView: ImageView, banners: String, i: Int) {
        IStringUtils.displayImage(context, banners, imageView)
    }

    override fun onBannerItemClick(bgaBanner: BGABanner, imageView: ImageView, banners: String, i: Int) {}
}
