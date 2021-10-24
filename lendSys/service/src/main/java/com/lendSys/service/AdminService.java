package com.lendSys.service;

import com.lendSys.entity.Users;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;

import java.util.List;

public interface AdminService {
    // get all userInfo
    public ResultVo userInfo();

    // Add new user
    public ResultVo addUserInfo(Users users);

    // user delete
    public ResultVo userRemove(String id);

    // get user info by pages
    public  ResultVo pageUserInfo(int current, int size);

    // get user info by id/name/level and by pages
    public ResultVo pageUserDetailInfo(int current, int size, UserVo userVo);

    //edit user info
    public ResultVo editUserInfo(Users users);

}
