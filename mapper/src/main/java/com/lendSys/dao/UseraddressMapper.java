package com.lendSys.dao;

import com.lendSys.entity.Useraddress;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseraddressMapper extends GeneralDao<Useraddress> {

    public List<Useraddress> selectDeaultAddress(int userId);
}