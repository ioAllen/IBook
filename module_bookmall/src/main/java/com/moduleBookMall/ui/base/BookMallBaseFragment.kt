package com.moduleBookMall.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.common.base.BaseFragment
import com.common.di.module.FragmentModule
import com.common.utils.CommonUtils
import com.moduleBookMall.di.component.BookMallConfigPersistentComponent
import com.moduleBookMall.di.component.BookMallFragmentComponent
import com.moduleBookMall.di.component.DaggerBookMallConfigPersistentComponent
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
abstract class BookMallBaseFragment : BaseFragment() {
    private var mBookMallFragmentComponent: BookMallFragmentComponent? = null
    private var mFragmentId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val bookMallConfigPersistentComponent: BookMallConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mFragmentId)) {
            Timber.i("Creating new BookMallConfigPersistentComponent id=%d", mFragmentId)
            bookMallConfigPersistentComponent = DaggerBookMallConfigPersistentComponent.builder().appComponent(CommonUtils.obtainAppComponentFromContext(context)).build()
            sComponentsMap[mFragmentId] = bookMallConfigPersistentComponent
        } else {
            Timber.i("Reusing BookMallConfigPersistentComponent id=%d", mFragmentId)
            bookMallConfigPersistentComponent = sComponentsMap[mFragmentId]
        }
        if (bookMallConfigPersistentComponent != null) {
            mBookMallFragmentComponent = bookMallConfigPersistentComponent.fragmentComponent(FragmentModule(this))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId)
    }

    override fun onDestroy() {
        Timber.i("Clearing BookMallConfigPersistentComponent id=%d", mFragmentId)
        sComponentsMap.remove(mFragmentId)
        super.onDestroy()
    }

    fun fragmentComponent(): BookMallFragmentComponent? {
        return mBookMallFragmentComponent
    }

    companion object {
        private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val NEXT_ID = AtomicLong(0)
        @SuppressLint("UseSparseArrays")
        private val sComponentsMap = HashMap<Long, BookMallConfigPersistentComponent>()
    }
}