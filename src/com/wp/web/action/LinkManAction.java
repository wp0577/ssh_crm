package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.LinkMan;
import com.wp.service.LinkManService;
import com.wp.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    private LinkMan linkMan = new LinkMan();

    private LinkManService linkManService;

    private Integer currentPage;

    private Integer pageSize;

    public String add() {
        System.out.println(linkMan.getLkm_id());
        System.out.println(linkMan.getCustomer().getCust_id());
        linkManService.add(linkMan);
        return "toList";
    }

    public String list() {
        //通过Detach离线对象去查询，可以实现多态
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        PageBean pageBean = linkManService.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        /*for (int i = 0; i < pageBean.getList().size(); i++) {
            Customer customer = (Customer) pageBean.getList().get(i);
            System.out.println(customer.getCust_level());
            //if(customer.getCust_level()!=null) System.out.println(customer.getCust_level().getDict_item_name());
        }*/
        return SUCCESS;
    }

    public String edit() {
        //如果要使用customer.getCust_Id()的方法的话，需要将list.jsp中传来的custID属性名改为cust_Id.
        linkMan = linkManService.getById(linkMan.getLkm_id());
        // Customer  customer = customerService.getById(Long.valueOf(ServletActionContext.getRequest().getParameter("custId")));
        ActionContext.getContext().put("linkMan", linkMan);
        return "toAdd";
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
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
}
