package com.moduleBookMall.jsoup

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jsoup.nodes.Document
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
class JsoupManager @Inject constructor() {
    private val compositeDisposable = CompositeDisposable()
    private val jsoupHandle = JsoupHandle()

    fun loadData(url: String) {
        compositeDisposable.add(jsoupHandle.getApi(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(Consumer<Document> { aData ->
                    analysisNewsUpdateData(aData)
                }))
    }

    /**
     * 解析最新更新数据
     */
    private fun analysisNewsUpdateData(document: Document) {
        val title = document.title()
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}