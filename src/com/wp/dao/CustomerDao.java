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

    public Integer getCustomerCount(DetachedCriteria dc) {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                String hql = "select count(*) from Customer";

                return (int) (long) session.createQuery(hql).uniqueResult();
            }
        });
    }

    public List<Customer> getList(DetachedCriteria dc, PageBean pageBean) {

        return (List<Customer>) getHibernateTemplate().findByCriteria(dc, pageBean.getStart(), pageBean.getPageSize());

    }
}
