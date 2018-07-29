package com.wp.dao;

import com.wp.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public class UserDao extends BaseDao<User> {

    public List<User> getUserByName(DetachedCriteria dc) {
        return (List<User>) getHibernateTemplate().findByCriteria(dc);


    }

}