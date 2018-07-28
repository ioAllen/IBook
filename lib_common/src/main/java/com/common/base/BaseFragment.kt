package com.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.common.utils.DialogHelper
import com.common.utils.IStringUtils
import com.trello.rxlifecycle2.components.support.RxFragment
import timber.log.Timber


/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
abstract class BaseFragment : RxFragment() {

    private var mView: View? = null
    private var mProgressDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(attachLayoutRes(), container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    protected abstract fun attachLayoutRes(): Int

    protected abstract fun initData()

    protected fun showProgressDialog(stringRes: Int, isCancel: Boolean) {
        dismissProgressDialog()
        if (activity != null) {
            mProgressDialog = DialogHelper.showIndeterminateProgressDialog(activity!!, getString(stringRes), false, isCancel)
        }
    }

    protected fun dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }


    protected fun showToast(msg: String) {
        if (context != null) {
            IStringUtils.showToast(context!!, msg)
        }
    }

    protected fun showToast(msg: Int) {
        if (context != null) {
            IStringUtils.showToast(context!!, msg)
        }
    }

    open fun handleError(code: Int, msg: Any?) {
        Timber.e(msg.toString())
        val message = msg.toString()
        showToast(message)
    }
}
