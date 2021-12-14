package com.lendSys.service.impl;

import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.utils.MD5;
import com.lendSys.utils.Sha1Utils;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    /*
    check user log in
     */
    @Override
    public ResultVo checkLogin(String username, String pwd) throws Exception {
        // based on the User to get the user info

        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",  username);
        List<Users> usersList = usersMapper.selectByExample(example);

        // check if user exit or not
        if(usersList.size() == 0){
            // user isn't exist
            return new ResultVo(1001,"User not Exist",0,null);
        }else{
            // encrypt the password
            // if password are same, they will get
            String sha1Pwd = Sha1Utils.shaEncode(pwd);
            Users user = new Users();
            if(sha1Pwd.equals(usersList.get(0).getUserpwd())){
                // log in success
                String token = username;
                return new ResultVo(1000,token,1,usersList.get(0));
            }else{
                // pwd is wrong
                return new ResultVo(1001,"Password is wrong",0,usersList.get(0));
            }

        }
    }

    public static boolean isNumeric(final String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
    /*
    get user information
     */
    @Override
    public ResultVo getUsrInfo(String token){
        String username = token;
        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",  username);
        List<Users> usersList = usersMapper.selectByExample(example);
        return new ResultVo(1000,token,1,usersList.get(0));
    }

}
