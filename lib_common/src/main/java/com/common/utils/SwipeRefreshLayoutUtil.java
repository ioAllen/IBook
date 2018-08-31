package com.common.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.common.R;


/**
 *
 * Created by liusaibao on 29/11/2017.
 */

public abstract class SwipeRefreshLayoutUtil implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public SwipeRefreshLayoutUtil(SwipeRefreshLayout swipeRefreshLayout) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.public_colorAccent);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void startRefresh() {
        if (this.mSwipeRefreshLayout != null && !this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    public void finishRefresh() {
        if (this.mSwipeRefreshLayout != null && this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void setEnabled(boolean enabled) {
        if (this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setEnabled(enabled);
        }
    }

    public boolean resetRefresh() {
        if (this.mSwipeRefreshLayout != null && this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            return true;
        }
        return false;
    }
}
