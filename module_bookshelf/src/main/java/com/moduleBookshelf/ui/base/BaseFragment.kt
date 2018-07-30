package com.moduleBookshelf.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseFragment
import com.common.di.module.FragmentModule
import com.common.utils.CommonUtils
import com.moduleBookshelf.di.component.ConfigPersistentComponent
import com.moduleBookshelf.di.component.DaggerConfigPersistentComponent
import com.moduleBookshelf.di.component.FragmentComponent
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class BaseFragment : BaseFragment() {
    private var mFragmentComponent: FragmentComponent? = null
    private var mFragmentId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mFragmentId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(context)).build()
            sComponentsMap[mFragmentId] = configPersistentComponent
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId)
            configPersistentComponent = sComponentsMap[mFragmentId]
        }
        if (configPersistentComponent != null) {
            mFragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId)
        sComponentsMap.remove(mFragmentId)
        super.onDestroy()
    }

    fun fragmentComponent(): FragmentComponent? {
        return mFragmentComponent
    }

    companion object {
        private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

}