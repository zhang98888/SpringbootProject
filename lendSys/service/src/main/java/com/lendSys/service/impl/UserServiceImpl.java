package com.lendSys.service.impl;

import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.utils.MD5;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Transactional
    public ResultVo userRegist(int userId,String name, String pwd) {
        synchronized (this) {
            // check the user name has been register or not
            Example example= new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", name);
            List<Users> usersList = usersMapper.selectByExample(example);
            // if not, need to save
            if (usersList.size() == 0) {
                String md5Pwd = MD5.md5(pwd);
                Users updateUser = new Users();
                updateUser.setUserid(userId);
                updateUser.setUsername(name);
                updateUser.setUserpwd(md5Pwd);
                updateUser.setUserRegtime(new Date());
                updateUser.setUserModtime(new Date());
                int check = usersMapper.insert(updateUser);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", null);
                } else {
                    return new ResultVo(1001, "Fail to register", null);
                }
            } else {
                return new ResultVo(1001, "User has been register", null);
            }
        }
    }

    @Override
    public ResultVo checkLogin(String userName, String pwd) {
        // based on the User to get the user info
        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", userName);
        List<Users> usersList = usersMapper.selectByExample(example);

        // check if user exit or not
        if(usersList.size() == 0){
            // user isn't exist
            return new ResultVo(1001,"User not Exist",null);
        }else{
            // encrypt the password
            // if password are same, they will get
            String md5Pwd = MD5.md5(pwd);
            Users user = new Users();
            if(md5Pwd.equals(usersList.get(0).getUserpwd())){
                // log in success
                return new ResultVo(1000,"Log In Success",usersList.get(0));
            }else{
                // pwd is wrong
                return new ResultVo(1001,"Password is wrong",usersList.get(0));
            }

        }
    }
}
