package com.lendSys.service.impl;

import com.lendSys.dao.UserDao;
import com.lendSys.entity.User;
import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public ResultVo checkLogin(String name, String pwd) {
        // based on the User to get the user info
        User user = userDao.searchUserByName(name);
        // check if user exit or not
        if(user == null){
            // user isn't exist
            return new ResultVo(1001,"User not Exist",null);
        }else{
            // encrypt the password
            // if password are same, they will get
            if(user.getUserPwd().equals(pwd)){
                // log in success
                return new ResultVo(1000,"Log In Success",user);
            }else{
                // pwd is wrong
                return new ResultVo(1001,"Password is wrong",user);
            }

        }
    }
}
