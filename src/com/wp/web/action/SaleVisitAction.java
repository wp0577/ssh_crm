package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.SaleVisit;
import com.wp.domain.User;
import com.wp.service.SaleVisitService;
import com.wp.service.UserService;
import com.wp.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private SaleVisit saleVisit = new SaleVisit();
    private SaleVisitService saleVisitService;
    private Integer currentPage;
    private Integer pageSize;
    private UserService userService;

    public String add() throws Exception {
        //获取当前登陆的用户
        User user = (User) ActionContext.getContext().getSession().get("user1");
        saleVisit.setUser(user);
        saleVisitService.save(saleVisit);
        return SUCCESS;
    }

    public String list() {
        //通过Detach离线对象去查询，可以实现多态
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        /*if (StringUtils.isNotBlank(saleVisit.getVisit_id())) {
            dc.add(Restrictions.like("lkm_name", "%" + saleVisit.getLkm_name() + "%"));
        }*/

        //判断是否需要通过客户去筛选
        if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
            dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
        }

        //判断是否有业务员去筛选
        if (saleVisit.getUser() != null && saleVisit.getUser().getUser_name() != null) {
            List<User> list = userService.getUserByName(saleVisit.getUser().getUser_name());
            Disjunction dis = Restrictions.disjunction();
            for (User user : list) {
                dis.add(Restrictions.eq("user.user_id", user.getUser_id()));
            }
            dc.add(dis);
        }

        PageBean pageBean = saleVisitService.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        /*for (int i = 0; i < pageBean.getList().size(); i++) {
            Customer customer = (Customer) pageBean.getList().get(i);
            System.out.println(customer.getCust_level());
            //if(customer.getCust_level()!=null) System.out.println(customer.getCust_level().getDict_item_name());
        }*/
        return "toList";
    }

    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }
}
