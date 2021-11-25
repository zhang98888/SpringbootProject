package com.lendSys.service.impl;
import com.alibaba.druid.util.StringUtils;;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
import com.lendSys.utils.MD5;
import com.lendSys.utils.Sha1Utils;
import com.lendSys.utils.WebStudentListener;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    public ResultVo addUserInfo(Users users) throws Exception {
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
                String sha1Pwd = Sha1Utils.shaEncode(pwd);
                users.setUserpwd(sha1Pwd);
                users.setUserRegtime(new Date());
                users.setUserModtime(new Date());
                int check = usersMapper.insert(users);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", check, users);
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
        if(userVo == null) {
            List<Users> usersList = usersMapper.selectAll();
            return new ResultVo(1000, "success", usersList.size(), usersList);
        }
        String username = userVo.getUsername();
        String userlevel = userVo.getUserlevel();
        if (userVo.getUserid() != null) {
            int userId = Integer.parseInt(userVo.getUserid());
            Users users = usersMapper.selectByPrimaryKey(userId);
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
    public ResultVo editUserInfo(Users users) throws Exception {
        String pwd = Sha1Utils.shaEncode(users.getUserpwd());
        users.setUserpwd(pwd);
        int check = usersMapper.updateByPrimaryKey(users);
        if(check == 0){
            return new ResultVo(1001, "Fail to update", 0, null);
        }else
            return new ResultVo(1000, "Success", 1, users);
    }


}
