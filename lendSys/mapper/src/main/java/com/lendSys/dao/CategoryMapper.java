package com.lendSys.dao;

import com.lendSys.entity.Category;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends GeneralDao<Category> {
}