package com.lendSys.service.impl;

import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
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
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public ResultVo userInfo() {
        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        List<Users> usersList = usersMapper.selectAll();
        return new ResultVo(1000, "Success!", usersList);
    }
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
}
