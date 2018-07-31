package com.wp.dao;

import com.wp.domain.BaseDict;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseDictDao extends BaseDao<BaseDict> {

    public List<BaseDict> getByCode(String code) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BaseDict.class);
        detachedCriteria.add(Restrictions.eq("dict_type_code", code));
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

}
