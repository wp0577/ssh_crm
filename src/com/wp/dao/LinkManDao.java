package com.wp.dao;

import com.wp.domain.LinkMan;

import java.io.Serializable;

public class LinkManDao extends BaseDao<LinkMan> {

    @Override
    public void saveOrUpdate(LinkMan o) {
        super.saveOrUpdate(o);
    }

    @Override
    public LinkMan getById(Serializable id) {
        return super.getById(id);
    }
}
