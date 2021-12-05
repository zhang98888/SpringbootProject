package com.lendSys;

import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
import com.lendSys.service.UserService;
import com.lendSys.utils.Sha1Utils;
import com.lendSys.vo.ResultVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {UsersMapper.class})
@Transactional
public class UserServiceTest {
    @Resource
    UserService userService;

    @MockBean(name = "usersMapper")
    private UsersMapper usersMapper;

    @BeforeEach
    public void before(){
        usersMapper = mock(UsersMapper.class);
    }

    @Test
    public void loginTest1() throws Exception {
        String username = "admin";
        String pwd = "111111";
        List<Users> usersList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        ResultVo resultVo = userService.checkLogin(username,pwd);
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void loginTest2() throws Exception {
        String username = "admin";
        String pwd = "111111";
        String sha1Pwd = Sha1Utils.shaEncode(pwd);
        List<Users> usersList = new ArrayList<>();
        Users users = new Users();
        users.setUserpwd(sha1Pwd);
        users.setUserid(100);
        users.setUsername(username);
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        ResultVo resultVo = userService.checkLogin(username,pwd);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void getUserInfoTest(){
        String username ="admin";
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        ResultVo resultVo = userService.getUsrInfo(username);
        Assert.assertEquals(1000,resultVo.getStatus());
    }
}
