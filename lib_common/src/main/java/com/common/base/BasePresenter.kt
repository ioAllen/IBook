package com.common.base

import android.support.annotation.NonNull
import com.common.utils.CommonConstant
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import okhttp3.MediaType
import okhttp3.RequestBody
import rx.Subscription
import timber.log.Timber
import java.util.HashMap


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView>() : Presenter<T>, Consumer<Throwable> {


    var mvpView: T? = null
        private set

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected var subscription: Subscription? = null

    private val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        compositeDisposable.clear()
        if (subscription != null) {
            subscription?.unsubscribe()
        }
        mvpView = null
    }

    @Throws(Exception::class)
    override fun accept(throwable: Throwable) {
        if (throwable is NullPointerException) {
            Timber.e(throwable)
            return
        }
        mvpView?.handleError(CommonConstant.ResponseCode.OTHER_ERROR_CODE, throwable.message)
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")


    fun toRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }

    fun createRequestBody(@NonNull s: String): RequestBody {
        return RequestBody.create(MediaType.parse("multipart/form-data"), s)
    }

    protected abstract inner class SuccessConsumer<K : Base> : Consumer<K> {
        @Throws(Exception::class)
        override fun accept(k: K) {
            if (k.code() == CommonConstant.ResponseCode.OK_CODE) {
                handleSuccess(k)
            } else {
                mvpView?.handleError(k.code(), k.message())
            }
        }

        protected abstract fun handleSuccess(k: K)
    }

    /**
     * 分页通用的传参
     * @param pageNum
     * @return
     */
    protected fun getPagingMap(pageNum: Int): HashMap<String, String> {
        val params = HashMap<String, String>()
        params["pageNum"] = pageNum.toString()
        params["pageSize"] = IPaging.PAGE_SIZE.toString()
        return params
    }
}