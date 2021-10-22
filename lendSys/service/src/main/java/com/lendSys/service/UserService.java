package com.lendSys.service;

import com.lendSys.vo.ResultVo;

public interface UserService {

    // user login
    public ResultVo checkLogin(String userName, String pwd);
}
