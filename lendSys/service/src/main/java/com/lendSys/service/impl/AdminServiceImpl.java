package com.lendSys.service.impl;
import com.alibaba.druid.util.StringUtils;;
import com.github.pagehelper.PageHelper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
import com.lendSys.utils.MD5;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.*;

@Service
@Scope("singleton")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultVo userInfo() {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        List<Users> usersList = usersMapper.selectAll();
        return new ResultVo(1000, "Success!", usersList.size(), usersList);
    }

    @Transactional
    public ResultVo addUserInfo(Users users) {
        synchronized (this) {
            // check the user name has been register or not
            String username = users.getUsername();
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", users.getUsername());
            List<Users> usersList = usersMapper.selectByExample(example);
            // if not, need to save
            if (usersList.size() == 0 && users.getDepartmentid() != 0) {
                String pwd = users.getUserpwd();
                String md5Pwd = MD5.md5(pwd);
                users.setUserpwd(md5Pwd);
                users.setUserRegtime(new Date());
                users.setUserModtime(new Date());
                int check = usersMapper.insert(users);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", check, null);
                } else {
                    return new ResultVo(1001, "Fail to register", 0, null);
                }
            } else if( users.getDepartmentid() == 0) {
                return new ResultVo(1001, "No department", 0, null);
            }else
                return new ResultVo(1001, "User has been register", 0, null);
        }
    }

    @Override
    public ResultVo userRemove(String id) {
        Integer userid = Integer.parseInt(id);
        int num = usersMapper.deleteByPrimaryKey(userid);
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);

    }

    @Override
    public ResultVo pageUserInfo(int current, int size) {
        PageHelper.startPage(current, size);
        List<Users> usersList = usersMapper.selectAll();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) usersList;
        int total = usersList.size();
        return new ResultVo(1000, "Success!", total, usersList);
    }

    @Override
    public ResultVo pageUserDetailInfo(int current, int size, UserVo userVo) {
        String username = userVo.getUsername();
        String userlevel = userVo.getUserlevel();
        if (userVo.getUserid() != 0) {
            Users users = usersMapper.selectByPrimaryKey(userVo.getUserid());
            List<Users> usersList = new ArrayList<>();
            usersList.add(users);
            return new ResultVo(1000, "Success!", 1, usersList);
        } else if(!StringUtils.isEmpty(username) || !StringUtils.isEmpty(userlevel)) {
            PageHelper.startPage(current, size);
            List<Users> usersList = null;
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userlevel)){
                usersList = usersMapper.findByLevelAndUsername(username,userlevel);
            }else if(!StringUtils.isEmpty(username)){
                criteria.andEqualTo("username", username);
                usersList = usersMapper.selectByExample(example);
            }else if(!StringUtils.isEmpty(userlevel)){
                criteria.andEqualTo("userLevel", userlevel);
                usersList = usersMapper.selectByExample(example);
            }

            com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) usersList;
            return new ResultVo(1000, "Success", usersList.size(), usersList);
        }else
            return new ResultVo(1000, "Success", 0, null);
    }

    @Override
    public ResultVo editUserInfo(Users users){
        int check = usersMapper.updateByPrimaryKey(users);
        if(check == 0){
            return new ResultVo(1001, "Fail to update", 0, null);
        }else
            return new ResultVo(1000, "Success", 1, users);
    }
}
