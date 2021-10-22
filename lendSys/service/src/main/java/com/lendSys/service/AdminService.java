package com.lendSys.service;

import com.lendSys.entity.Users;
import com.lendSys.vo.ResultVo;

import java.util.List;

public interface AdminService {

    public List<Users> userInfo();
    // user register
    public ResultVo userRegist(int userId,String userName, String pwd);
}
