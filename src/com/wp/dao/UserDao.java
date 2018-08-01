package com.wp.dao;

import com.wp.domain.User;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class UserDao extends BaseDao<User> {

    public List<User> getUserByName(DetachedCriteria dc) {
        return (List<User>) getHibernateTemplate().findByCriteria(dc);
    }

    public List<User> getUserByCode(String code) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_code", code));
        return (List<User>) getHibernateTemplate().findByCriteria(dc);
    }

    public List<User> getUserByName(String name) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        System.out.println("name" + name);
        dc.add(Restrictions.like("user_name", "%" + name + "%"));
        return (List<User>) getHibernateTemplate().findByCriteria(dc);
    }
}
