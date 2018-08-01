package com.common.utils

import android.os.Environment

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton

/**
 * sd卡存储
 */
@Singleton
class StorageUtil @Inject
constructor() {

    private var rootDir: File = File(Environment.getExternalStorageDirectory(), "iBook")

    init {
        if (!rootDir.exists()) rootDir.mkdir()
    }

    fun getPath(dirEnum: DirEnum): String {
        val path = File(rootDir, dirEnum.dir)
        if (!path.exists()) path.mkdir()
        return path.path
    }

    enum class DirEnum private constructor(val dir: String) {

        images("Images"), avatars("Avatars"), apks("Apks"), error("error"),cache("cache")
    }
}
