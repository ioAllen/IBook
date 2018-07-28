package com.android.iBook.ui.base

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.android.iBook.R
import com.common.adapter.TabFragmentAdapter
import com.common.base.BaseActivity
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Route(path = RouterHub.APP_MAIN_ACTIVITY)
class MainActivity : BaseActivity() {

    override fun attachLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        setSupportActionBar(toolbar)
//        setActionBarElevation()
        initTab()
    }

    private fun initTab() {
        val tabList = ArrayList<String>()
        tabList.add(getString(R.string.app_bookshelf))
        tabList.add(getString(R.string.app_bookMall))
        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        val fragmentList = ArrayList<Fragment>()
        val fragment1 = CommonUtils.obtainARouterFragment(RouterHub.BOOKSHELF_MAIN_FRAGMENT)
        val fragment2 = CommonUtils.obtainARouterFragment(RouterHub.BOOKSHELF_MAIN_FRAGMENT)
        fragmentList.add(fragment1)
        fragmentList.add(fragment2)

        val fragmentAdapter = TabFragmentAdapter(supportFragmentManager, fragmentList, tabList)
        mViewPager.adapter = fragmentAdapter//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager)//将TabLayout和ViewPager关联起来。
    }
}
