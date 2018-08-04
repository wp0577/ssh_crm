package com.wp.dao;

import com.wp.domain.Customer;
import com.wp.util.PageBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
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

    public List getIndustryCount() {

        List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            String sql = "SELECT bd.dict_item_name, count(*) FROM " +
                    "cst_customer cc, base_dict bd " +
                    "where cc.cust_industry=bd.dict_id " +
                    "GROUP BY bd.dict_item_name";

            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql);
                return query.list();
            }
        });
        //System.out.println(list);
        return list;
    }

    public List getSourceCount() {
        List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            String sql = "SELECT bd.dict_item_name, count(*) FROM " +
                    "cst_customer cc, base_dict bd " +
                    "where cc.cust_source=bd.dict_id " +
                    "GROUP BY bd.dict_item_name";

            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql);
                return query.list();
            }
        });
        //System.out.println(list);
        return list;
    }


    @Override
    public void save(Customer customer) {
        super.save(customer);
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        super.saveOrUpdate(customer);
    }

    @Override
    public Integer getCount(DetachedCriteria dc) {
        return super.getCount(dc);
    }

    @Override
    public List<Customer> getList(DetachedCriteria dc, PageBean pageBean) {
        return super.getList(dc, pageBean);
    }

    @Override
    public Customer getById(Serializable id) {
        return super.getById(id);
    }
}
