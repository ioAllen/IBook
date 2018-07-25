package com.common.utils

import android.content.Context
import android.os.Handler
import android.widget.Toast

/**
 * Toast
 */
object ToastHelper {

    fun show(context: Context, text: CharSequence) {
        toast(context, text.toString())
    }

    fun show(context: Context, resId: Int) {
        val text = context.resources.getString(resId)
        toast(context, text)
    }

    private fun toast(context: Context, msg: String) {
        val handler = Handler(context.mainLooper)
        handler.post {
            if (!IStringUtils.isNullOrEmpty(msg)) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

}

