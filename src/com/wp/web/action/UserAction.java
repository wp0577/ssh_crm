package com.wp.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wp.domain.User;
import com.wp.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private UserService userService;
    //如果要用模型驱动，需要使用User user = new User();
    //而且模型驱动似乎跟DetachedCriteria dc =
    // DetachedCriteria.forClass(User.class);有冲突。

    private User user = new User();

    public String login() {
        //System.out.println("模型驱动" + user.getUser_name());
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_code", user.getUser_code()));
        dc.add(Restrictions.eq("user_password", user.getUser_password()));
        try {
            List<User> list = userService.getByName(dc);
            ActionContext.getContext().getSession().put("user1", list.get(0));
            return SUCCESS;
        } catch (Exception e) {
            ActionContext.getContext().put("error", e.getMessage());
            return "error";
        }
    }

    public String regist() throws Exception {
        try {
            userService.regist(user);
        } catch (Exception e) {
            ActionContext.getContext().put("error", e.getMessage());
            System.out.println(e.getMessage());
            return "errorRegist";
        }
        return "toLogin";
    }


    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
