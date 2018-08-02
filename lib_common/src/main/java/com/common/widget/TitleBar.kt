package com.common.widget

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.common.R
import kotlinx.android.synthetic.main.view_action_title.view.*

/**
 * author：WangLei
 * date:2017/12/8.
 * QQ:619321796
 */
class TitleBar : LinearLayout, View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.head_break -> {
                if (backClickListener != null) {
                    backClickListener!!.onBack()
                } else {
                    val activity = context as Activity
                    activity.finish()
                }
            }
            R.id.titleRightTxt -> {
                if (onRightClick != null) {
                    onRightClick!!.onRightClick()
                }
            }
            R.id.right_icon -> {
                if (onRightClick != null) {
                    onRightClick!!.onRightClick()
                }
            }
        }
    }

    constructor(context: Context) : super(context) {
        initView(context, null)
    }


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_action_title, this)
        gravity = Gravity.FILL_HORIZONTAL
        head_break.setOnClickListener(this)
        titleRightTxt.setOnClickListener(this)
        right_icon.setOnClickListener(this)
    }

    /**
     * 设置返回按钮是否显示
     */
    fun showViewBreak(show: Boolean) {
        if (show) {
            head_break.visibility = View.VISIBLE
        } else {
            head_break.visibility = View.GONE
        }
    }

    fun setRightButtonResource(resource: Int, onRightClickListener: OnRightClickListener) {
        right_icon.setImageResource(resource)
        showRightButton(true, onRightClickListener)
    }

    fun showRightButton(boolean: Boolean, onRightClickListener: OnRightClickListener) {
        if (boolean) {
            right_icon.visibility = View.VISIBLE
            onRightClick = onRightClickListener
        } else {
            right_icon.visibility = View.GONE
        }
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        titleName.text = title
    }

    fun setTitle(title: Int) {
        titleName.text = resources.getString(title)
    }

    fun setTitleColor(color: Int) {
        titleName.setTextColor(color)
    }

    /**
     * 设置右边的文字
     */
    fun setRightTxt(title: String, onRightClickListener: OnRightClickListener) {
        titleRightTxt.text = title
        titleRightTxt.visibility = View.VISIBLE
        onRightClick = onRightClickListener
    }

    private var onRightClick: OnRightClickListener? = null

    interface OnRightClickListener {
        fun onRightClick()
    }

    private var backClickListener: OnBackClickListener? = null
    fun setOnBackListener(backClickListener: OnBackClickListener) {
        this.backClickListener = backClickListener
        head_break.visibility = View.VISIBLE
    }

    interface OnBackClickListener {
        fun onBack()
    }

    fun setTitleBarBackgroundBitmap(bitmap: Bitmap?) {
        if (bitmap != null) {
            backgroundIv.setImageBitmap(bitmap)
        }
    }

    fun getBackgroundImageView(): ImageView {
        return backgroundIv
    }
}