package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.Customer;
import org.apache.commons.lang3.StringUtils;
import com.wp.service.CustomerService;
import com.wp.util.PageBean;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();
    private CustomerService customerService;
    //使用struts框架的特性，通过属性查询来直接得到需要的属性内容
    //Integer currentPage = (Integer)ActionContext.getContext().get("currentPage");
    private Integer currentPage;
    private Integer pageSize;

    private File photo;

    public String getIndustryCount() {
        //传回来的是List<Obeject[]>对象，列表中有两个数组对象。
        List list = customerService.getIndustryCount();
        ActionContext.getContext().put("list", list);
        return "toMultiple";
    }

    public String getSourceCount() {
        //传回来的是List<Obeject[]>对象，列表中有两个数组对象。
        List list = customerService.getSourceCount();
        ActionContext.getContext().put("list", list);
        return "toMultiple1";
    }

    public String list() throws Exception {

        //通过Detach离线对象去查询，可以实现多态
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        PageBean pageBean = customerService.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        /*for (int i = 0; i < pageBean.getList().size(); i++) {
            Customer customer = (Customer) pageBean.getList().get(i);
            System.out.println(customer.getCust_level());
            //if(customer.getCust_level()!=null) System.out.println(customer.getCust_level().getDict_item_name());
        }*/
        return SUCCESS;

    }

    public String save() {
        //文件上传
        if (photo != null) photo.renameTo(new File("/Users/wupan/Desktop/test.jpg"));
        customerService.save(customer);
        return "toList";
    }

    public String edit() {
        //如果要使用customer.getCust_Id()的方法的话，需要将list.jsp中传来的custID属性名改为cust_Id.
        customer = customerService.getById(customer.getCust_id());
        // Customer  customer = customerService.getById(Long.valueOf(ServletActionContext.getRequest().getParameter("custId")));
        ActionContext.getContext().put("customer", customer);
        System.out.println(customer);
        return "toAdd";
    }

    @Override
    public Customer getModel() {
        return customer;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
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
