package com.common.jsoup

import io.reactivex.ObservableOnSubscribe
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
class JsoupHandle {

    private var jsoupHeader = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"

    fun getApi(url: String): io.reactivex.Observable<Document> {
        return io.reactivex.Observable.create(ObservableOnSubscribe<Document> { emitter ->
            try {
                val success = connect(url).get()
                emitter.onNext(success)
            } catch (e: IOException) {
                e.printStackTrace()
                emitter.onError(e)
            }
        });
    }

    private fun connect(url: String): Connection {
        return Jsoup.connect(url.trim { it <= ' ' }).header("User-Agent", jsoupHeader)
    }

}