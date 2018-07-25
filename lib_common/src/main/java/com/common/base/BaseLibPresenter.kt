package com.common.base

import android.support.annotation.NonNull
import com.common.data.remote.BaseAPI
import com.common.utils.BaseConstant
import com.common.marathon.ui.base.LibPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import rx.Subscription
import timber.log.Timber

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BaseLibPresenter<T : LibMvpView>(protected val baseApi: BaseAPI) : LibPresenter<T>, Consumer<Throwable> {

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
        mvpView?.handleError(BaseConstant.ResponseCode.OTHER_ERROR_CODE, throwable.message)
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

    protected abstract inner class SuccessConsumer<K : ResponseBody> : Consumer<K> {
        @Throws(Exception::class)
        override fun accept(k: K) {
            val json = k.string()
            val jsonObj = JSONObject(json)
            val code = jsonObj.getInt("ret")
            if (code == BaseConstant.ResponseCode.OK_CODE|| code == BaseConstant.ResponseCode.CAN_REGISTER) {
                handleSuccess(json)
            } else {
                val errDesc = jsonObj.getString("errDesc")
                mvpView?.handleError(code, errDesc)
            }
        }

        protected abstract fun handleSuccess(json: String)
    }
}