package com.moduleBookshelf.di.component


import com.common.di.PerFragment
import com.common.di.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface BookMallFragmentComponent {
}
