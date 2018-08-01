package com.common.utils.glide

import android.content.Context
import android.os.Environment
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.common.base.BaseApplication
import com.common.utils.StorageUtil

@GlideModule
internal class CustomAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {

        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))

        val MAX_CACHE_SIZE = 100 * 1024 * 1024
        val CACHE_FILE_NAME = "imgCache"

        builder.setDiskCache(ExternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE))
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            val dir = BaseApplication[context].getAppComponent().storageUtil().getPath(StorageUtil.DirEnum.cache)
            //路径---->sdcard/imgCache
            builder.setDiskCache(DiskLruCacheFactory(dir, MAX_CACHE_SIZE.toLong()))
        } else {
            //路径---->/sdcard/Android/data/<application package>/cache/imgCache
            builder.setDiskCache(ExternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE))
        }
    }

    /**
     * 关闭解析AndroidManifest
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

}