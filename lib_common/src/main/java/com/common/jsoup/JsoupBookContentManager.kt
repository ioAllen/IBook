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
class JsoupBookContentManager @Inject constructor() {
    private val compositeDisposable = CompositeDisposable()
    private val jsoupHandle = JsoupHandle()
    private var onBookContentAnalysisListener: OnBookContentAnalysisListener? = null;
    private lateinit var bookNative: BookNative


    fun loadBookContent(url: String) {
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
                        analysisBookContent(aData)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    private fun analysisBookContent(document: Document) {
        val content = document.select("#content").html();
        onBookContentAnalysisListener?.onBookContentAnalysisSuccess(content)
    }

    fun setOnBookContentAnalysisListener(onBookContentAnalysisListener: OnBookContentAnalysisListener) {
        this.onBookContentAnalysisListener = onBookContentAnalysisListener
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}