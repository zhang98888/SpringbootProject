package com.lendSys.dao;

import com.lendSys.entity.ProductPicture;
import com.lendSys.generalDao.GeneralDao;
import com.lendSys.vo.CategoryPicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPictureMapper extends GeneralDao<ProductPicture> {

    public List<CategoryPicVo> selectCategoryPic(int categoryId);

    public List<CategoryPicVo> productPic();
}