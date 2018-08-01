package com.wp.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wp.domain.User;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //获得session并从session中得到user1对象
        User user = (User) ActionContext.getContext().getSession().get("user1");
        //判断user是否为空，为空则toLogin，else则放行
        if (user == null) return "toLogin";
        else return invocation.invoke();
    }
}
