package com.wp.service;

import com.wp.dao.BaseDictDao;
import com.wp.domain.BaseDict;

import java.util.List;

public class BaseDictService {
    private BaseDictDao baseDictDao;

    public List<BaseDict> getByCode(String code) {
        return baseDictDao.getByCode(code);
    }

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }
}
