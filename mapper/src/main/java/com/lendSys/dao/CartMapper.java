package com.lendSys.dao;

import com.lendSys.entity.Cart;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper extends GeneralDao<Cart> {

    public List<Cart> selectByName(String username);
    public List<Cart> selectByProductAndName(int userid,String username);
}