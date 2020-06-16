package com.zhizhi.wrapper;

import com.zhizhi.model.Pageable;

import java.util.List;

/**
 * Page泛型类，用于分页返回查询数据
 * @param <T> 实现了Pageable接口的model类
 */
public class Page<T extends Pageable> {

    private Integer totalRecordNum; // 数据库中所有的记录数

    private Integer totalPageNum; // 总共可以分多少页

    private Integer currentPageIndex; // 当前页码

    private Integer pageSize = 10; // 页面大小，即包含的记录数

    private List<T> currentPage; // 当前页面包含的记录组成的列表

    public Integer getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(Integer totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Integer getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(Integer currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(List<T> currentPage) {
        this.currentPage = currentPage;
    }
}
