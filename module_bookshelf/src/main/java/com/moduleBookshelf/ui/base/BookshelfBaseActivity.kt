package com.moduleBookshelf.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseActivity
import com.common.di.module.ActivityModule
import com.common.utils.CommonUtils
import com.moduleBookshelf.di.component.BookshelfActivityComponent
import com.moduleBookshelf.di.component.BookshelfConfigPersistentComponent
import com.moduleBookshelf.di.component.DaggerBookshelfConfigPersistentComponent
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class BookshelfBaseActivity : BaseActivity() {

    private var mBookshelfActivityComponent: BookshelfActivityComponent? = null
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val bookshelfConfigPersistentComponent: BookshelfConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new BookshelfConfigPersistentComponent id=%d", mActivityId)
            bookshelfConfigPersistentComponent = DaggerBookshelfConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(this)).build()
            sComponentsMap.put(mActivityId, bookshelfConfigPersistentComponent)
        } else {
            Timber.i("Reusing BookshelfConfigPersistentComponent id=%d", mActivityId)
            bookshelfConfigPersistentComponent = sComponentsMap[mActivityId]
        }
        if (bookshelfConfigPersistentComponent != null) {
            mBookshelfActivityComponent = bookshelfConfigPersistentComponent.activityComponent(ActivityModule(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putLong(KEY_ACTIVITY_ID, mActivityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing BookshelfConfigPersistentComponent id=%d", mActivityId)
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): BookshelfActivityComponent? {
        return mBookshelfActivityComponent
    }

    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, BookshelfConfigPersistentComponent>()
    }
}