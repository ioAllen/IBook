package com.common.jsoup

import com.common.data.bean.BookNative
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.nodes.Document
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
class JsoupNewsUpdateManager @Inject constructor() {
    private val compositeDisposable = CompositeDisposable()
    private val jsoupHandle = JsoupHandle()
    private var onBookDataAnalysisListener: OnBookDataAnalysisListener? = null;

    fun loadData(url: String) {
//        compositeDisposable.add(jsoupHandle.getApi(url)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(Consumer<Document?> { aData ->
//                    if (aData != null) {
//                        analysisNewsUpdateData(aData)
//                    }
//                }))

        jsoupHandle.getApi(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Document> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(aData: Document) {
                        analysisNewsUpdateData(aData)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    /**
     * 解析最新更新数据
     */
    private fun analysisNewsUpdateData(document: Document) {
        val select = document.select("div.up").select("div.l").first().getElementsByTag("li")
        val list = ArrayList<BookNative>()
        for (element in select.iterator()) {
            val itemS2 = element.select("span.s2").select("a")
            val itemS3 = element.select("span.s3").select("a")
            val itemS4 = element.select("span.s4")
            val itemS5 = element.select("span.s5")
            val book = BookNative()
            book.bookName = itemS2.text()
            book.newsUpdateContent = itemS3.text()
            book.author = itemS4.text()
            book.bookLinkPath = ArrayList()
            val path = itemS2.attr("abs:href")
            book.bookLinkPath!!.add(path)
            list.add(book)
        }
        onBookDataAnalysisListener?.onBookAnalysisSuccess(list)
    }

    fun setOnBookDataAnalysisListener(onBookDataAnalysisListener: OnBookDataAnalysisListener) {
        this.onBookDataAnalysisListener = onBookDataAnalysisListener
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}