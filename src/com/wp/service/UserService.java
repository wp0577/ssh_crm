package com.wp.service;

import com.wp.dao.UserDao;
import com.wp.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public List<User> getByName(DetachedCriteria dc) throws Exception {
        List<User> list = userDao.getUserByName(dc);
        if (list.size() > 0)
        return userDao.getUserByName(dc);
        else throw new Exception("username or password is wrong");

    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void regist(User user) {
        System.out.println(user.getUser_code());
        //first check the userCode whether exist
        List<User> list = userDao.getUserByCode(user.getUser_code());
        if (list.size() > 0) throw new RuntimeException("username has existed !!!");
        user.setUser_state('1');
        userDao.save(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
