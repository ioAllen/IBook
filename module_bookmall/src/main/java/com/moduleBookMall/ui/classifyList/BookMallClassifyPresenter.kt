package com.moduleBookMall.ui.classifyList

import com.common.base.BasePresenter
import com.moduleBookMall.data.modle.BookData
import com.moduleBookMall.data.remote.MallApi
import com.moduleBookMall.utils.JsonUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class BookMallClassifyPresenter @Inject constructor(private var api: MallApi, var jsonUtil: JsonUtil) : BasePresenter<BookMallClassifyMvpView>() {

    fun loadBookByType(bookType: String, pageNum: Int) {
        checkViewAttached()
        val params = getPagingMap(pageNum)
        params["bookType"] = bookType
        compositeDisposable.add(api.loadBookByType(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(io.reactivex.functions.Consumer<ResponseBody> { t ->
                    val book = jsonUtil.fromJson(t.string(), BookData::class.java)
                    if (book.data() != null) {
                        mvpView?.loadBookByTypeSuccess(book)
                    } else {
                        mvpView?.loadBookByTypeFailure()
                    }
                }, this))
//                .subscribe(object : SuccessConsumer<BookData>() {
//                    override fun handleSuccess(k: BookData) {
//                        mvpView?.loadBookByTypeSuccess(k)
//                    }
//                }, this))
    }

}