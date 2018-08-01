package com.wp.service;

import com.wp.dao.SaleVisitDao;
import com.wp.domain.LinkMan;
import com.wp.domain.SaleVisit;
import com.wp.domain.User;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class SaleVisitService {
    private SaleVisitDao saleVisitDao;

    public void save(SaleVisit saleVisit) {
        saleVisitDao.saveOrUpdate(saleVisit);
    }

    public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
        this.saleVisitDao = saleVisitDao;
    }

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //通过离线查询对象查询
        Integer totalCount = saleVisitDao.getCount(dc);
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        List<SaleVisit> saleVisits = saleVisitDao.getList(dc, pageBean);
        pageBean.setList(saleVisits);
        return pageBean;
    }

}
