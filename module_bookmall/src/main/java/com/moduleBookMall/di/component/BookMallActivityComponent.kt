package com.moduleBookMall.di.component


import com.common.di.PerActivity
import com.common.di.module.ActivityModule
import com.moduleBookMall.ui.classify.BookMallClassifyActivity
import com.moduleBookMall.ui.classifyList.BookMallClassifyListActivity
import com.moduleBookMall.ui.find.BookMallFindActivity
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface BookMallActivityComponent {

    fun inject(bookMallClassifyActivity: BookMallClassifyActivity)
    fun inject(bookMallClassifyListActivity: BookMallClassifyListActivity)
    fun inject(bookMallFindActivity: BookMallFindActivity)
}
