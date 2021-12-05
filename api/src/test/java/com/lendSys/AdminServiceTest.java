package com.lendSys;

import com.lendSys.dao.UseraddressMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.AddressService;
import com.lendSys.service.AdminService;
import com.lendSys.service.impl.AdminServiceImpl;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {UsersMapper.class})
@Transactional
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;


    @MockBean(name = "usersMapper")
    private UsersMapper usersMapper;

    @BeforeEach
    public void before(){
        adminService = mock(AdminService.class);
        usersMapper = mock(UsersMapper.class);
    }

    @Test
    public void userInfo(){
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectAll()).thenReturn(usersList);
        ResultVo resultVo = adminService.userInfo();
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void addUserInfo() throws Exception {
        Users users = new Users();
        users.setUserid(1000);
        users.setDepartmentid(200);
        users.setUserpwd("123456");
        List<Users> usersList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(usersMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = adminService.addUserInfo(users);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void addUserInfo2() throws Exception {
        Users users = new Users();
        users.setUserid(1000);
        users.setDepartmentid(200);
        users.setUserpwd("123456");
        List<Users> usersList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(usersMapper.insert(any())).thenReturn(0);
        ResultVo resultVo = adminService.addUserInfo(users);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void addUserInfo3() throws Exception {
        Users users = new Users();
        users.setUserid(1000);
        users.setDepartmentid(200);
        users.setUserpwd("123456");
        List<Users> usersList = new ArrayList<>();
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(usersMapper.insert(any())).thenReturn(0);
        ResultVo resultVo = adminService.addUserInfo(users);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void addUserInfo4() throws Exception {
        Users users = new Users();
        users.setUserid(1000);
        users.setDepartmentid(0);
        users.setUserpwd("123456");
        List<Users> usersList = new ArrayList<>();
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(usersMapper.insert(any())).thenReturn(0);
        ResultVo resultVo = adminService.addUserInfo(users);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void userRemoveTest(){
        Mockito.when(usersMapper.deleteByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = adminService.userRemove("1");
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void userRemoveTest2(){
        Mockito.when(usersMapper.deleteByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = adminService.userRemove("1");
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void editUserInfoTest() throws Exception{
        Users users = new Users();
        users.setUserpwd("123456");
        List<Users> usersList = new ArrayList<>();
        Mockito.when(usersMapper.selectByPrimaryKey(any())).thenReturn(users);
        Mockito.when(usersMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = adminService.editUserInfo(users);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void editUserInfoTest2() throws Exception{
        Users users = new Users();
        users.setUserpwd("123456");
        Users users1 = new Users();
        users1.setUserpwd("111111");
        List<Users> usersList = new ArrayList<>();
        Mockito.when(usersMapper.selectByPrimaryKey(any())).thenReturn(users1);
        Mockito.when(usersMapper.updateByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = adminService.editUserInfo(users);
        Assert.assertEquals(0,resultVo.getTotal());
    }


}
