package com.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author：WangLei
 * date:2018/7/25.
 * QQ:619321796
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = attachLayoutRes()
        if (layout != 0) {
            setContentView(layout)
        }
        initData()
    }

    /**
     * 注册界面setContentView的xml
     */
    protected abstract fun attachLayoutRes(): Int

    protected abstract fun initData()
}