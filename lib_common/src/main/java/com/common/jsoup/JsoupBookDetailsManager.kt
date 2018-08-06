package com.common.jsoup

import com.common.data.bean.BookCatalogue
import com.common.data.bean.BookNative
import com.common.utils.CommonConstant
import com.common.utils.IStringUtils
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
class JsoupBookDetailsManager @Inject constructor() {
    private val compositeDisposable = CompositeDisposable()
    private val jsoupHandle = JsoupHandle()
    private var onBookDetailsAnalysisListener: OnBookDetailsAnalysisListener? = null;
    private lateinit var bookNative: BookNative

    /**
     * 加载书籍基本信息
     */
    fun loadData(bookNative: BookNative) {
        this.bookNative = bookNative
        val bookPath = bookNative.bookLinkPath
        if (bookPath != null && bookPath.isNotEmpty() && IStringUtils.isNullOrEmpty(bookNative.coverPath)) {
            val url = bookPath[0]
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
                            analysisBookDetails(aData)
                        }

                        override fun onError(e: Throwable) {
                        }

                    })
        }
    }

    /**
     * 加载书籍目录信息
     */
    fun loadBookCatalogue(bookNative: BookNative) {
        this.bookNative = bookNative
        val bookPath = bookNative.bookLinkPath
        if (bookPath != null && bookPath.isNotEmpty()) {
            val url = bookPath[0]
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
                            analysisBookCatalogue(aData)
                        }

                        override fun onError(e: Throwable) {
                        }

                    })
        }
    }

    /**
     * 解析书本详情数据
     */
    private fun analysisBookDetails(document: Document) {
        analysisBookData(document)
        onBookDetailsAnalysisListener?.onBookAnalysisSuccess(bookNative)
    }

    private fun analysisBookData(document: Document) {
        val select = document.getElementsByAttributeValue("class", "cover").first().getElementsByTag("img")
        val intro = document.getElementsByAttributeValue("class", "intro")
        var path = select.attr("src")
        var info = intro.html()
        if (info.contains("<br>")) {
            info = info.substring(0, info.indexOf("<br>"))
        }
        if (!IStringUtils.isNullOrEmpty(path) && !path.contains(CommonConstant.biquge)) {
            path = CommonConstant.biquge + path
        }
        bookNative.coverPath = path
        bookNative.bookDescription = info
    }

    /**
     * 解析目录数据
     */
    private fun analysisBookCatalogue(document: Document) {
        if (IStringUtils.isNullOrEmpty(bookNative.coverPath)) {
            analysisBookData(document)
        }
        val listMain = document.getElementsByAttributeValue("class", "listmain").first().getElementsByTag("dd")
        val bookCatalogueList = ArrayList<BookCatalogue>()
        for (item in listMain.listIterator()) {
            val c = item.select("a")
            val title = c.text()
            val p = c.attr("abs:href")
            bookCatalogueList.add(BookCatalogue(title, p))
        }
        bookNative.bookCatalogue = bookCatalogueList
        onBookDetailsAnalysisListener?.onBookAnalysisSuccess(bookNative)
    }

    fun setOnBookDetailsAnalysisListener(onBookDetailsAnalysisListener: OnBookDetailsAnalysisListener) {
        this.onBookDetailsAnalysisListener = onBookDetailsAnalysisListener
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}