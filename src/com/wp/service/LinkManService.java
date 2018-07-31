package com.wp.service;

import com.wp.dao.LinkManDao;
import com.wp.domain.Customer;
import com.wp.domain.LinkMan;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class LinkManService {
    private LinkManDao linkManDao;

    public void add(LinkMan linkMan) {
        linkManDao.saveOrUpdate(linkMan);
    }

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //通过离线查询对象查询
        Integer totalCount = linkManDao.getCount(dc);
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        if (pageSize == null) pageBean.setPageSize(3);
        List<LinkMan> linkMEN = linkManDao.getList(dc, pageBean);
        pageBean.setList(linkMEN);
        System.out.println(pageSize);
        return pageBean;
    }

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    public LinkMan getById(Long lkm_id) {
        return linkManDao.getById(lkm_id);
    }
}
