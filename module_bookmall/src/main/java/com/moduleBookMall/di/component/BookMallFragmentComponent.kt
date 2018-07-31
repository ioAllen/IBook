package com.moduleBookMall.di.component


import com.common.di.PerFragment
import com.common.di.module.FragmentModule
import com.moduleBookMall.ui.bookMall.BookMallFragment
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface BookMallFragmentComponent {

    fun inject(bookMallFragment: BookMallFragment)
}
