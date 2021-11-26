package com.lendSys.dao;

import com.lendSys.entity.Users;
import com.lendSys.generalDao.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper extends GeneralDao<Users> {

    public List<Users> findByLevelAndUsername(@Param("username") String username ,
                                              @Param("userLevel") String userLevel);

}