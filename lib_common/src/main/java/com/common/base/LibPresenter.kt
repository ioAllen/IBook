package com.common.marathon.ui.base

import com.common.base.LibMvpView

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface LibPresenter<V : LibMvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}
