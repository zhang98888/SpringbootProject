package com.lendSys.dao;

import com.lendSys.entity.ProductPicture;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPictureMapper extends GeneralDao<ProductPicture> {
}