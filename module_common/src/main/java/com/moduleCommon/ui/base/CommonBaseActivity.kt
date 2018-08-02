package com.moduleCommon.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseActivity
import com.common.di.module.ActivityModule
import com.common.utils.CommonUtils
import com.moduleCommon.di.CommonActivityComponent
import com.moduleCommon.di.CommonConfigPersistentComponent
import com.moduleCommon.di.DaggerCommonConfigPersistentComponent
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class CommonBaseActivity : BaseActivity() {

    private var mBookshelfActivityComponent: CommonActivityComponent? = null
    private var mActivityId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val commonConfigPersistentComponent: CommonConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new BookshelfConfigPersistentComponent id=%d", mActivityId)
            commonConfigPersistentComponent = DaggerCommonConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(this)).build()
            sComponentsMap.put(mActivityId, commonConfigPersistentComponent)
        } else {
            Timber.i("Reusing BookshelfConfigPersistentComponent id=%d", mActivityId)
            commonConfigPersistentComponent = sComponentsMap[mActivityId]
        }
        if (commonConfigPersistentComponent != null) {
            mBookshelfActivityComponent = commonConfigPersistentComponent.activityComponent(ActivityModule(this))
        }
        super.onCreate(savedInstanceState)
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

    fun activityComponent(): CommonActivityComponent? {
        return mBookshelfActivityComponent
    }

    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, CommonConfigPersistentComponent>()
    }
}