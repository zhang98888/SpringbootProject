package com.lendSys.dao;

import com.lendSys.entity.Product;
import com.lendSys.generalDao.GeneralDao;
import com.lendSys.vo.detailProductVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDao<Product> {

    public List<detailProductVo> getDetailProduct(int productId);
}