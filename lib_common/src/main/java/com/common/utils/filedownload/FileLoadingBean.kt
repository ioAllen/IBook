package com.common.utils.filedownload

class FileLoadingBean(total: Long, progress: Long) {
    /**
     * 文件大小
     */
    var total: Long = 0
        internal set
    /**
     * 已下载大小
     */
    var progress: Long = 0
        internal set

    init {
        this.total = total
        this.progress = progress
    }
}
