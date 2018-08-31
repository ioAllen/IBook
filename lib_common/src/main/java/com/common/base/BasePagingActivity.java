package com.common.base;

import android.os.Bundle;

import com.common.utils.loadmorewrapper.LoadMoreAdapter;


/**
 * 用于分页的Activity
 * Created by LiuSaibao on 9/28/2017.
 */

public abstract class BasePagingActivity extends BaseActivity implements IPaging {

    private PagingUtil pagingUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pagingUtil = new PagingUtil();
        super.onCreate(savedInstanceState);
    }

    /**
     * 加载更多数据需要设置总页数
     *
     * @param pages
     */
    @Override
    public void setPages(int pages) {
        pagingUtil.setPages(pages);
    }

    /**
     * 获取最新数据需要重置
     */
    @Override
    public void resetPageNum() {
        pagingUtil.resetPageNum();
    }

    /**
     * 设置下一页
     */
    @Override
    public void setNextPageNum() {
        pagingUtil.setNextPageNum();
    }

    /**
     * 获取当前页
     *
     * @return
     */
    @Override
    public int getCurrentPageNum() {
        return pagingUtil.getCurrentPageNum();
    }

    @Override
    public void noMoreData() {
        if (getLoadMoreAdapter() != null) {
            getLoadMoreAdapter().setLoadMoreEnabled(false);
            if (!getLoadMoreAdapter().getShowNoMoreEnabled()) getLoadMoreAdapter().setShowNoMoreEnabled(true);
            getLoadMoreAdapter().getOriginalAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (pagingUtil.getPages() >= getCurrentPageNum()) {
            onLoadMore();
        } else {
            if (getAdapter() != null && getAdapter().isLoading()) {
//                if (getAdapter().onCreateLayoutHelper() != null
//                        && getAdapter().onCreateLayoutHelper() instanceof StaggeredGridLayoutHelper) {
//                    getAdapter().loadMoreEnd(true);
//                } else {
//                    getAdapter().loadMoreEnd();
//                }
                getAdapter().loadMoreEnd();
            }
            noMoreData();
        }
    }

    @Override
    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
        if (enabled.getLoadMoreEnabled()) {
            onLoadMoreRequested();
        } else {
            if (getLoadMoreAdapter() != null) {
                getLoadMoreAdapter().getOriginalAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public LoadMoreAdapter getLoadMoreAdapter() {
        return null;
    }
}
