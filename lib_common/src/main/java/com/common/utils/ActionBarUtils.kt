package com.common.utils

import android.os.Build
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.common.R
import com.common.widget.TitleBar

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
class ActionBarUtils constructor(var activity: AppCompatActivity) {

    /**
     * 头部栏
     */
    var actionbarView: TitleBar? = null

    /**
     * 标题栏添加返回键和去除ActionBar底部的阴影部分
     */
    fun addBackEvent(layoutResID: Int, noStatusBar: Boolean) {
        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            setActionBarElevation()
            addCustomView(actionBar)
        }
        val color = activity.resources.getColor(R.color.public_colorPrimary)
        StatusBarUtil.setColor(activity, color, 0)
        StatusBarUtil.setLightMode(activity)

        if (noStatusBar) {
            val viewGroup = activity.findViewById<View>(android.R.id.content) as ViewGroup
            viewGroup.removeAllViews()
            val parentLinearLayout = LinearLayout(activity)
            parentLinearLayout.orientation = LinearLayout.VERTICAL
            parentLinearLayout.isClickable = true
            parentLinearLayout.fitsSystemWindows = true
            viewGroup.addView(parentLinearLayout)
            LayoutInflater.from(activity).inflate(layoutResID, parentLinearLayout, true)
        }
    }

    /**
     * 添加自定义actionView
     */
    private fun addCustomView(actionBar: ActionBar) {
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val params = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER)
        actionbarView = LayoutInflater.from(activity).inflate(R.layout.action_title, LinearLayout(activity), false) as TitleBar
        actionBar.setCustomView(actionbarView, params)
        val parent = actionbarView?.parent
        if (parent is Toolbar) {
            parent.setContentInsetsAbsolute(0, 0)
        }
    }

    /**
     * 去除ActionBar底部的阴影部分
     */
    private fun setActionBarElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val actionBar = activity.supportActionBar
            if (actionBar != null) {
                actionBar.elevation = 1f
            }
        }
    }
}