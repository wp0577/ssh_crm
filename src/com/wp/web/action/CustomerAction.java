package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.Customer;
import org.apache.commons.lang3.StringUtils;
import com.wp.service.CustomerService;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();
    private CustomerService customerService;
    //使用struts框架的特性，通过属性查询来直接得到需要的属性内容
    //Integer currentPage = (Integer)ActionContext.getContext().get("currentPage");
    private Integer currentPage;
    private Integer pageSize;

    public String list() throws Exception {

        //通过Detach离线对象去查询，可以实现多态
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        PageBean pageBean = customerService.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;

    }

    @Override
    public Customer getModel() {
        return customer;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
