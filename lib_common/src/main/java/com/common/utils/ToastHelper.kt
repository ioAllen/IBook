package com.common.utils

import android.content.Context
import android.widget.Toast
import com.common.base.BaseApplication
import com.common.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Toast
 */
@Singleton
class ToastHelper @Inject
constructor(@param:ApplicationContext private val context: Context) {

    fun show(title: String?) {
        showToast(title, Toast.LENGTH_SHORT)
    }


    fun show(resId: Int) {
        showToast(context.resources.getText(resId), Toast.LENGTH_SHORT)
    }

    fun show(resId: Int, duration: Int) {
        showToast(context.resources.getText(resId), duration)
    }

    @JvmOverloads
    fun showToast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
        BaseApplication[context].mHandler.post {
            if (text != null && !IStringUtils.isNullOrEmpty(text.toString())) {
                Toast.makeText(context, text, duration).show()
            }
        }
    }

    fun show(resId: Int, vararg args: Any) {
        show(String.format(context.resources.getString(resId), *args), Toast.LENGTH_SHORT)
    }

    fun show(format: String, vararg args: Any) {
        show(String.format(format, *args), Toast.LENGTH_SHORT)
    }

    fun show(resId: Int, duration: Int, vararg args: Any) {
        show(String.format(context.resources.getString(resId), *args), duration)
    }

    fun show(format: String, duration: Int, vararg args: Any) {
        showToast(String.format(format, *args), duration)
    }
}