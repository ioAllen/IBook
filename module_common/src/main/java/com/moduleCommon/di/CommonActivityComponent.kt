package com.moduleCommon.di


import com.common.di.PerActivity
import com.common.di.module.ActivityModule
import com.moduleCommon.ui.bookDetails.CommonBookDetailsActivity
import com.moduleCommon.ui.launch.CommonLaunchActivity
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface CommonActivityComponent {
    fun inject(commonLaunchActivity: CommonLaunchActivity)
    fun inject(commonBookDetailsActivity: CommonBookDetailsActivity)
}
