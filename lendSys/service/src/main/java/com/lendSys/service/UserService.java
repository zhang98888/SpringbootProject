package com.lendSys.service;

import com.lendSys.entity.User;
import com.lendSys.vo.ResultVo;

public interface UserService {

    public ResultVo checkLogin(String name, String pwd);

}
