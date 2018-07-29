package com.wp.dao;

import com.wp.domain.Customer;
import com.wp.util.PageBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CustomerDao extends BaseDao<Customer> {


    public Customer checkCustomer(String username) {
        return getHibernateTemplate().execute(new HibernateCallback<Customer>() {
            @Override
            public Customer doInHibernate(Session session) throws HibernateException {
                String hql = "from User where user_name = ?";
                Query query = session.createQuery(hql);
                query.setParameter(0, username);
                return (Customer) query.uniqueResult();
            }
        });
    }

    @Override
    public Integer getCount(DetachedCriteria dc) {
        return super.getCount(dc);
    }

    @Override
    public List<Customer> getList(DetachedCriteria dc, PageBean pageBean) {
        return super.getList(dc, pageBean);
    }
}
