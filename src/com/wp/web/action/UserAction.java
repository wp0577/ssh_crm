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

    private User user;
    private String user_name;
    private String user_password;

    public String login() {
        //System.out.println("模型驱动" + user.getUser_name());
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_name", user_name));
        dc.add(Restrictions.eq("user_password", user_password));
        List<User> list = userService.getByName(dc);
        if (list.size() > 0) {
            ActionContext.getContext().getSession().put("user1", list.get(0));
            return SUCCESS;
        }
        return ERROR;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
