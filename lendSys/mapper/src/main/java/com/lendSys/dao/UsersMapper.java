package com.lendSys.dao;

import com.lendSys.entity.Users;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends GeneralDao<Users> {
}