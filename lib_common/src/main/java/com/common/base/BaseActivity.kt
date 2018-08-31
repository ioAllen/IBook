package com.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.common.utils.ActionBarUtils
import com.common.utils.DialogHelper
import com.common.utils.IStringUtils
import com.common.utils.SwipeRefreshLayoutUtil
import com.common.widget.TitleBar
import timber.log.Timber

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 加载中对话框，无进度
     */
    private var mProgressDialog: MaterialDialog? = null

    lateinit var actionBarUtils: ActionBarUtils

    protected var mSwipeRefreshLayoutUtil: SwipeRefreshLayoutUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        val layout = attachLayoutRes()
        actionBarUtils = ActionBarUtils(this)
        if (layout != 0) {
            setContentView(layout)
            actionBarUtils.addBackEvent(layout, noStatusBar())
        }
        injection()
        initData()
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
        actionBarUtils.actionbarView?.showViewBreak(show)
    }

    /**
     * 设置头部标题
     */
    fun setTitleTxt(title: String?) {
        actionBarUtils.actionbarView?.setTitle(title)
    }

    fun setTitleTxt(title: Int) {
        setTitleTxt(getString(title))
    }

    fun getActionBarView(): TitleBar? {
        return actionBarUtils.actionbarView
    }


    /**
     * 注册界面setContentView的xml
     */
    protected abstract fun attachLayoutRes(): Int

    protected abstract fun initData()

    /**
     * 注入dagger，选择性重写
     */
    protected open fun injection() {

    }

    /**
     * 去除状态栏
     */
    protected open fun noStatusBar(): Boolean {
        return true
    }

    fun handleError(code: Int, msg: Any?) {
        mSwipeRefreshLayoutUtil?.finishRefresh()
        Timber.e(msg.toString())
        dismissProgressDialog()
        val message = msg.toString()
        showToast(message)
    }

    /**
     * 重提示（对话框提示）
     */
    fun powerfulHints(code: Int, msg: Any?) {

    }

}