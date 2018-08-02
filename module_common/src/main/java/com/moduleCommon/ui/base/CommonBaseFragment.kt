package com.moduleCommon.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseFragment
import com.common.di.module.FragmentModule
import com.common.utils.CommonUtils
import com.moduleCommon.di.CommonConfigPersistentComponent
import com.moduleCommon.di.CommonFragmentComponent
import com.moduleCommon.di.DaggerCommonConfigPersistentComponent
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class CommonBaseFragment : BaseFragment() {
    private var mCommonFragmentComponent: CommonFragmentComponent? = null
    private var mFragmentId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val commonConfigPersistentComponent: CommonConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mFragmentId)) {
            Timber.i("Creating new BookshelfConfigPersistentComponent id=%d", mFragmentId)
            commonConfigPersistentComponent = DaggerCommonConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(context)).build()
            sComponentsMap[mFragmentId] = commonConfigPersistentComponent
        } else {
            Timber.i("Reusing BookshelfConfigPersistentComponent id=%d", mFragmentId)
            commonConfigPersistentComponent = sComponentsMap[mFragmentId]
        }
        if (commonConfigPersistentComponent != null) {
            mCommonFragmentComponent = commonConfigPersistentComponent.fragmentComponent(FragmentModule(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        Timber.i("Clearing BookshelfConfigPersistentComponent id=%d", mFragmentId)
        sComponentsMap.remove(mFragmentId)
        super.onDestroy()
    }

    fun fragmentComponent(): CommonFragmentComponent? {
        return mCommonFragmentComponent
    }

    companion object {
        private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, CommonConfigPersistentComponent>()
    }

}