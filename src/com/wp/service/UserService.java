package com.wp.service;

import com.wp.dao.UserDao;
import com.wp.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public List<User> getByName(DetachedCriteria dc) {
        return userDao.getUserByName(dc);

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
