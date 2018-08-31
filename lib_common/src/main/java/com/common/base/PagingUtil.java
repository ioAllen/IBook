package com.common.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分页工具类
 * Created by LiuSaibao on 9/28/2017.
 */

public class PagingUtil {

    private int pages;

    /**
     * 获取总页数
     * @return
     */
    public int getPages() {
        return pages;
    }

    /**
     * 加载更多数据需要设置总页数
     * @param pages
     */
    protected void setPages(int pages) {
        this.pages = pages;
    }

    private AtomicInteger mPageNum = new AtomicInteger(1);

    /**
     * 获取最新数据需要重置
     */
    protected void resetPageNum() {
        mPageNum.set(1);
    }

    /**
     * 设置下一页
     */
    protected void setNextPageNum() {
        mPageNum.incrementAndGet();
    }

    /**
     * 获取当前页
     * @return
     */
    protected int getCurrentPageNum() {
        return mPageNum.get();
    }
}
