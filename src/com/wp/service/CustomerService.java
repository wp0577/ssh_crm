package com.wp.service;

import com.wp.dao.CustomerDao;
import com.wp.domain.Customer;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class CustomerService {
    CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //查询总数据和Customer对象，并封装成pageBean
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //通过离线查询对象查询
        Integer totalCount = customerDao.getCustomerCount(dc);
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        List<Customer> customers = customerDao.getList(dc, pageBean);
        pageBean.setList(customers);
        return pageBean;
    }

}