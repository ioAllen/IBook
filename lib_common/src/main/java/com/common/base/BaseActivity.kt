package com.common.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.common.R
import com.common.utils.DialogHelper
import com.common.utils.IStringUtils
import com.common.utils.StatusBarUtil
import com.common.widget.TitleBar

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 头部栏
     */
    var actionbarView: TitleBar? = null

    /**
     * 加载中对话框，无进度
     */
    private var mProgressDialog: MaterialDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = attachLayoutRes()
        if (layout != 0) {
            setContentView(layout)
        }
        addBackEvent()
        initData()
    }

    /**
     * 注册界面setContentView的xml
     */
    protected abstract fun attachLayoutRes(): Int

    protected abstract fun initData()


    /**
     * 标题栏添加返回键和去除ActionBar底部的阴影部分
     */
    private fun addBackEvent() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            setActionBarElevation()
            addCustomView(actionBar)
        }
        StatusBarUtil.setTranslucent(this, 0)
        StatusBarUtil.setLightMode(this)
    }

    /**
     * 添加自定义actionView
     */
    private fun addCustomView(actionBar: ActionBar) {
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val params = ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER)
        actionbarView = LayoutInflater.from(this).inflate(R.layout.action_title, LinearLayout(this), false) as TitleBar
        actionBar.setCustomView(actionbarView, params)
        val parent = actionbarView?.parent
        if (parent is Toolbar) {
            parent.setContentInsetsAbsolute(0, 0)
        }
    }

    /**
     * 去除ActionBar底部的阴影部分
     */
    fun setActionBarElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val actionBar = supportActionBar
            if (actionBar != null) {
                actionBar.elevation = 0f
            }
        }
    }

    protected fun showToast(msg: String) {
        IStringUtils.showToast(this, msg)
    }

    protected fun showToast(msg: Int) {
        IStringUtils.showToast(this, msg)
    }

    protected fun showProgressDialog(stringRes: Int, isCancel: Boolean) {
        dismissProgressDialog()
        mProgressDialog = DialogHelper.showIndeterminateProgressDialog(this, getString(stringRes), false, isCancel)
    }

    protected fun showProgressDialog(stringRes: String, isCancel: Boolean) {
        dismissProgressDialog()
        mProgressDialog = DialogHelper.showIndeterminateProgressDialog(this, stringRes, false, isCancel)
    }

    protected fun dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }

    /**
     * 是否显示头部返回按钮
     */
    fun showViewBreak(show: Boolean) {
        actionbarView?.showViewBreak(show)
    }

    /**
     * 设置头部标题
     */
    fun setTitle(title: String) {
        actionbarView?.setTitle(title)
    }

    fun getActionBarView(): TitleBar? {
        return actionbarView
    }
}