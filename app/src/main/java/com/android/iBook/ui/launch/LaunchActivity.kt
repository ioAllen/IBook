package com.android.iBook.ui.launch

import com.alibaba.android.arouter.facade.annotation.Route
import com.android.iBook.R
import com.common.base.BaseActivity
import com.common.base.BaseApplication
import com.common.core.RouterHub
import com.common.utils.CommonUtils
import com.common.utils.StatusBarUtil

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
@Route(path = RouterHub.APP_LAUNCH_ACTIVITY)
class LaunchActivity : BaseActivity() {


    override fun attachLayoutRes(): Int {
        return R.layout.activity_launch
    }

    override fun initData() {
        StatusBarUtil.setTranslucent(this)
        BaseApplication[this].mHandler.postDelayed({
            CommonUtils.navigation(this, RouterHub.APP_MAIN_ACTIVITY)
            finish()
        }, 2000)
    }

}