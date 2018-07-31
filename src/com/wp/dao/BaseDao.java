package com.wp.dao;

import com.wp.domain.Customer;
import com.wp.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> extends HibernateDaoSupport {

    Class clazz;

    public BaseDao() {

        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) parameterizedType.getActualTypeArguments()[0];

    }

    //saveOrUpdate()方法：如果id相同则直接update而不是save一个新的数据
    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    //delete by id
    //because String, Integer and so on which can be used as ID
    // are all extends from serializable
    public void delete(Serializable id) {
        T t = this.getById(id);
        getHibernateTemplate().delete(t);
    }

    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    public T getById(Serializable id) {
        //该方法直接有通过id得到对象
        return (T) getHibernateTemplate().get(clazz, id);
    }

    public Integer getCount(DetachedCriteria dc) {

        dc.setProjection(Projections.rowCount());
        List list = getHibernateTemplate().findByCriteria(dc);
        //we should set dc projection to null
        dc.setProjection(null);
        if (list == null) return null;
        return (int) (long) list.get(0);

    }

    public List<T> getList(DetachedCriteria dc, PageBean pageBean) {
        return (List<T>) getHibernateTemplate().findByCriteria(dc, pageBean.getStart(), pageBean.getPageSize());
    }

}
