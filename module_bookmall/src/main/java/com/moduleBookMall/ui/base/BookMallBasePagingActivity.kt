package com.moduleBookMall.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseActivity
import com.common.base.BasePagingActivity
import com.common.di.module.ActivityModule
import com.common.utils.CommonUtils
import com.moduleBookMall.di.component.BookMallActivityComponent
import com.moduleBookMall.di.component.BookMallConfigPersistentComponent
import com.moduleBookMall.di.component.DaggerBookMallConfigPersistentComponent
import com.moduleBookMall.di.module.BookMallModule
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class BookMallBasePagingActivity : BasePagingActivity() {

    private var mBookMallActivityComponent: BookMallActivityComponent? = null
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val bookMallConfigPersistentComponent: BookMallConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new BookMallConfigPersistentComponent id=%d", mActivityId)
            bookMallConfigPersistentComponent = DaggerBookMallConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(this)).build()
            sComponentsMap.put(mActivityId, bookMallConfigPersistentComponent)
        } else {
            Timber.i("Reusing BookMallConfigPersistentComponent id=%d", mActivityId)
            bookMallConfigPersistentComponent = sComponentsMap[mActivityId]
        }
        if (bookMallConfigPersistentComponent != null) {
            mBookMallActivityComponent = bookMallConfigPersistentComponent.activityComponent(ActivityModule(this), BookMallModule(this))
        }
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing BookMallConfigPersistentComponent id=%d", mActivityId)
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): BookMallActivityComponent? {
        return mBookMallActivityComponent
    }

    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, BookMallConfigPersistentComponent>()
    }
}