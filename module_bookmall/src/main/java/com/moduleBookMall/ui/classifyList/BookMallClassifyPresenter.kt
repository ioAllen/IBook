package com.moduleBookMall.ui.classifyList

import com.common.base.BasePresenter
import com.moduleBookMall.data.modle.BookData
import com.moduleBookMall.data.remote.MallApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.function.Consumer
import javax.inject.Inject

class BookMallClassifyPresenter @Inject constructor(private var api: MallApi) : BasePresenter<BookMallClassifyMvpView>() {

    fun loadBookByType(bookType: String, pageNum: Int) {
        checkViewAttached()
        val params = getPagingMap(pageNum)
        params["bookType"] = bookType
        compositeDisposable.add(api.loadBookByType(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(io.reactivex.functions.Consumer<ResponseBody> { t ->
                    val json = t.string()
                    println(json)
                }, this))
//                .subscribe(object : SuccessConsumer<BookData>() {
//                    override fun handleSuccess(k: BookData) {
//                        mvpView?.loadBookByTypeSuccess(k)
//                    }
//                }, this))
    }

}