package com.wp.util;

import java.util.List;

public class PageBean {

    private Integer currentPage;
    private Integer totalCount;
    private Integer pageSize;
    private Integer totalPage;
    private List list;

    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        //if currentPage or pageSize is null
        //and currentPage must larger than 0
        //**赋值时一定要记得用this.field.因为currentPage和this.currenPage并不一样
        if (currentPage == null || currentPage < 1) this.currentPage = 1;
        if (pageSize == null || pageSize < 1) this.pageSize = 5;
        //用这种方法可以防止最后一页缺少的临界条件
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

        //check whether currentPage larger than totalCount/pageSize
        if (this.currentPage > this.totalPage) this.currentPage = this.totalPage;

    }

    public Integer getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
