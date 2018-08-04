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

    public List getIndustryCount() {
        return customerDao.getIndustryCount();
    }

    public List getSourceCount() {
        return customerDao.getSourceCount();
    }

    //查询总数据和Customer对象，并封装成pageBean

    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //通过离线查询对象查询
        Integer totalCount = customerDao.getCount(dc);
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        List<Customer> customers = customerDao.getList(dc, pageBean);
        pageBean.setList(customers);
       /* for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println(customer.getCust_level());
            //if(customer.getCust_level()!=null) System.out.println(customer.getCust_level().getDict_item_name());
        }*/
        return pageBean;
    }


    public void save(Customer customer) {
        customerDao.saveOrUpdate(customer);
    }

    public Customer getById(Long custId) {
        return customerDao.getById(custId);
    }
}
