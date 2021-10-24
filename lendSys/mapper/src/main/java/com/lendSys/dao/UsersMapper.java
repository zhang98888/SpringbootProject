package com.lendSys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lendSys.entity.Users;
import com.lendSys.generalDao.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper extends GeneralDao<Users> {

    public List<Users> findByLevelAndUsername( @Param("username") String username ,
                                               @Param("userLevel") String userLevel);

}