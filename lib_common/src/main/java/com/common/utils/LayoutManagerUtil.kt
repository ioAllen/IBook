package com.common.utils

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 *
 * Created by liusaibao on 28/11/2017.
 */

object LayoutManagerUtil {

    val staggeredGridLayoutManager: StaggeredGridLayoutManager
        get() = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    fun getVerticalLinearLayoutManager(context: Context?): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    fun getHorizontalLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun getGridLayoutManager(context: Context, spanCount: Int): GridLayoutManager {
        return GridLayoutManager(context, spanCount)
    }
}
