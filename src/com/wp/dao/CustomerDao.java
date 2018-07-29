package com.wp.dao;

import com.wp.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CustomerDao extends HibernateDaoSupport {


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

    public Integer getCustomerCount() {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                String hql = "select count(*) from Customer";

                return (int) (long) session.createQuery(hql).uniqueResult();
            }
        });
    }

    public List<Customer> getList(Integer currentPage, Integer pageSize) {
        return getHibernateTemplate().execute(new HibernateCallback<List<Customer>>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                String hql = "from Customer";
                Query query = session.createQuery(hql);
                query.setFirstResult((currentPage - 1) * pageSize + 1);
                query.setMaxResults(pageSize);
                query.setParameter(0, currentPage);
                return query.list();
            }
        });

    }
}
