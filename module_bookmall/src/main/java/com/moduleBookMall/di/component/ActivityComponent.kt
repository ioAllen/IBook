package com.moduleBookMall.di.component


import com.common.di.PerActivity
import com.common.di.module.ActivityModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

}
