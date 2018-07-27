package com.common.di.module

import android.support.v4.app.Fragment

import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val mFragment: Fragment) {

    @Provides
    internal fun provideFragment(): Fragment {
        return mFragment
    }
}
