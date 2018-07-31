package com.wp.service;

import com.wp.dao.LinkManDao;
import com.wp.domain.LinkMan;

public class LinkManService {
    private LinkManDao linkManDao;

    public void add(LinkMan linkMan) {
        linkManDao.saveOrUpdate(linkMan);
    }

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }
}
