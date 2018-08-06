package com.common.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.common.R

/**
 * author：WangLei
 * date:2017/9/30.
 * QQ:619321796
 * 对话框辅助类
 */
object DialogHelper {

    /**
     * 单选列表对话框
     */
    fun showSingleChoiceDialog(activity: Activity, title: String, content: List<String>, defaultIndex: Int, positiveText: String, negativeText: String, listCallbackSingleChoice: MaterialDialog.ListCallbackSingleChoice): MaterialDialog {
        return MaterialDialog.Builder(activity)
                .title(title)
                .items(content)
                .itemsCallbackSingleChoice(defaultIndex, listCallbackSingleChoice)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .negativeColor(activity.resources.getColor(R.color.color_ff222222))
                .positiveColor(activity.resources.getColor(R.color.color_ffb93221))
                .show()
    }

    /**
     * 对话框中添加view
     */
    fun showCustomViewDialog(activity: Context, title: String, positiveText: String, negativeText: String, customView: View, singleButtonCallback: MaterialDialog.SingleButtonCallback): MaterialDialog {
        return MaterialDialog.Builder(activity)
                .title(title)
                .titleGravity(GravityEnum.CENTER)
                .customView(customView, false)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .negativeColor(activity.resources.getColor(R.color.color_ff222222))
                .positiveColor(activity.resources.getColor(R.color.color_ffb93221))
                .onPositive(singleButtonCallback)
                .show()
    }

    /**
     * 加载进度对话框提示，无进度
     */
    fun showIndeterminateProgressDialog(activity: Context, content: String, horizontal: Boolean, cancelable: Boolean): MaterialDialog {
        return MaterialDialog.Builder(activity)
                .content(content)
                .progress(true, 0)
                .cancelable(cancelable)
                .progressIndeterminateStyle(horizontal)
                .show()
    }

    fun showProgressDialog(context: Context, title: String, content: String, positiveText: String, cancelable: Boolean?, buttonCallback: MaterialDialog.SingleButtonCallback): MaterialDialog {
        return showProgressDialog(context, title, content, positiveText, context.getString(R.string.cancel), cancelable, buttonCallback)
    }

    fun showProgressDialog(context: Context, title: String, content: String, positiveText: String, negative: String, cancelable: Boolean?, buttonCallback: MaterialDialog.SingleButtonCallback): MaterialDialog {
        return MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .cancelable(cancelable!!)
                .negativeText(negative)
                .progress(false, 100, false)
                .autoDismiss(false)
                .onPositive(buttonCallback)
                .canceledOnTouchOutside(false)
                .positiveText(positiveText)
                .progressIndeterminateStyle(true)
                .onNegative { dialog, which -> dialog.dismiss() }
                .show()
    }

    fun showDownloadDialogMinMaxProgress(activity: Activity, dialog: MaterialDialog?, current: Long, total: Long) {
        if (dialog != null) {
            activity.runOnUiThread {
                val str = activity.getString(R.string.download_file_size)
                val progressMinMax = dialog.view.findViewById<TextView>(R.id.md_minMax)
                progressMinMax.visibility = View.VISIBLE
                progressMinMax.text = String.format(str, FileUtil.loadFileSize(current), FileUtil.loadFileSize(total))
            }
        }
    }

    /**
     * 列表对话框
     *
     * @param activity
     * @param title
     * @param items
     * @param listCallback
     * @return
     */
    fun showListDialog(activity: Activity, title: String, items: Array<String>, listCallback: MaterialDialog.ListCallback): MaterialDialog {
        return MaterialDialog.Builder(activity)
                .title(title)
                .items(*items)
                .itemsCallback(listCallback)
                .show()
    }

    fun getNumberProgressDialog(activity: Activity, title: String, content: String, total: Int, cancelable: Boolean): MaterialDialog {
        return MaterialDialog.Builder(activity)
                .title(title)
                .content(content)
                .cancelable(cancelable)
                .contentGravity(GravityEnum.CENTER)
                .progress(false, total).show()
    }


    /***
     * 不能取消的普通对话框
     */
    fun showNoCancelHintDialog(activity: Activity, content: String, positiveText: String, buttonCallback: MaterialDialog.SingleButtonCallback): MaterialDialog {
        val builder = MaterialDialog.Builder(activity)
                .content(content)
                .positiveText(positiveText)
                .onPositive(buttonCallback)
        builder.canceledOnTouchOutside(false)
        val materialDialog = builder.show()
        materialDialog.setOnKeyListener { dialog, keyCode, event -> true }
        return materialDialog
    }


    /**
     * 通用对话框
     *
     * @param activity     Activity
     * @param title        标题
     * @param content      内容
     * @param positiveText 按钮文本
     * @return MaterialDialog
     */
    fun showCommonDialog(context: Context, title: String, content: String, positiveText: String, buttonCallback: MaterialDialog.SingleButtonCallback): MaterialDialog {
        return MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .negativeText(R.string.cancel)
                .onPositive(buttonCallback)
                .show()
    }
}
