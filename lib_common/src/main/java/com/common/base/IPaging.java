package com.common.base;


import com.common.library.adapter.base.BaseQuickAdapter;
import com.common.utils.loadmorewrapper.LoadMoreAdapter;

/**
 * 分页
 * Created by LiuSaibao on 9/28/2017.
 */

public interface IPaging extends BaseQuickAdapter.RequestLoadMoreListener, LoadMoreAdapter.OnLoadMoreListener {
    int PAGE_SIZE = 20;

    /**
     * 加载更多数据需要设置总页数
     * @param pages
     */
    void setPages(int pages);

    /**
     * 获取最新数据需要重置
     */
    void resetPageNum();

    /**
     * 设置下一页
     */
    void setNextPageNum();

    /**
     * 获取当前页
     * @return
     */
    int getCurrentPageNum();

    /**
     * 加载更多数据
     */
    void onLoadMore();

    /**
     * 没有更多的数据
     */
    void noMoreData();

    /**
     * 获取adapter实例
     */
    BaseQuickAdapter getAdapter();

    LoadMoreAdapter getLoadMoreAdapter();
}