package com.common.utils.filedownload


import android.content.Context
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Okio
import java.io.IOException


class FileResponseBody(internal var originalResponse: Response, var mContext: Context, var onDownloadListener: FileDownloadUtil.OnDownloadListener?) : ResponseBody() {

    override fun contentType(): MediaType? {
        return originalResponse.body()!!.contentType()
    }

    override fun contentLength(): Long {
        return originalResponse.body()!!.contentLength()
    }

    override fun source(): BufferedSource {
        return Okio.buffer(object : ForwardingSource(originalResponse.body()!!.source()) {
            internal var bytesReaded: Long = 0

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                bytesReaded += if (bytesRead == -1L) 0 else bytesRead
                if (onDownloadListener != null) {
                    val total = contentLength()
                    val processInt = bytesReaded * 100 / total
                    onDownloadListener!!.onDownloadProgress(processInt, bytesReaded, total)
                }
                return bytesRead
            }
        })
    }
}
