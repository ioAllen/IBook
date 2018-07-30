package com.common.utils

import android.content.Context
import android.widget.ImageView
import com.common.R
import com.common.base.BaseApplication
import com.common.utils.glide.GlideImgHelper

object IStringUtils {

    /**
     * 是否为空或空字符串
     */
    fun isNullOrEmpty(str: String?): Boolean {
        return null == str || "null" == str || str.trim { it <= ' ' }.isEmpty()
    }

    /**
     * 根据分号拆分src
     */
    fun srcSplit(str: String, sign: String): Array<String> {
        return if (!isNullOrEmpty(str) && str.contains(sign)) {
            str.split(sign.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else arrayOf(str)
    }

    /**
     * 取得一个string字符串防止空值
     */
    fun getString(value: String): String {
        return if (!isNullOrEmpty(value)) {
            value
        } else ""
    }

    fun showToast(context: Context, msg: String) {
        BaseApplication[context].getAppComponent().toastHelper().show(msg)
    }

    fun showToast(context: Context, msg: Int) {
        BaseApplication[context].getAppComponent().toastHelper().show(msg)
    }

    /**
     * 显示通用的图片
     *
     */
    fun displayImage(context: Context, path: String?, imageView: ImageView) {
        if (!isNullOrEmpty(path)) {
            GlideImgHelper.loadImage(context, path!!, imageView)
        } else {
            imageView.setImageResource(R.drawable.default_image)
        }
    }

    /**
     * 显示头像的图片
     *
     */
    fun displayAvatarImage(context: Context, path: String?, imageView: ImageView) {
        if (!isNullOrEmpty(path)) {
            GlideImgHelper.loadAvatarImage(context, path!!, imageView)
        } else {
            imageView.setImageResource(R.drawable.contact_list_pic_big)
        }
    }


}
