package com.lendSys.dao;

import com.lendSys.entity.Product;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends GeneralDao<Product> {
}