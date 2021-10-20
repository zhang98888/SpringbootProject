package com.lendSys.service;

import com.lendSys.vo.ResultVo;

public interface UserService {
    // user register
    public ResultVo userRegist(int userId,String userName, String pwd);
    // user login
    public ResultVo checkLogin(String userName, String pwd);

}
