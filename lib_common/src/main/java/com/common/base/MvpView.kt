package com.common.base


/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
interface MvpView {

    fun handleError(code: Int, msg: Any?)

    /**
     * 重提示（对话框提示）
     */
    fun powerfulHints(code: Int, msg: Any?)
}