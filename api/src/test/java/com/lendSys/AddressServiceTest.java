package com.lendSys;

import com.lendSys.dao.CartMapper;
import com.lendSys.dao.UseraddressMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Useraddress;
import com.lendSys.entity.Users;
import com.lendSys.service.AddressService;
import com.lendSys.vo.AddressVo;
import com.lendSys.vo.ResultVo;
import org.junit.Assert;
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

import static org.mockito.Mockito.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {UseraddressMapper.class, UsersMapper.class})
@Transactional
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @MockBean(name = "addressMapper")
    private UseraddressMapper addressMapper;

    @MockBean(name = "usersMapper")
    private UsersMapper usersMapper;

    @BeforeEach
    public void before(){
        addressService = mock(AddressService.class);
        addressMapper = mock(UseraddressMapper.class);
        usersMapper = mock(UsersMapper.class);
    }

    @Test
    public void getAddressByNameTest(){
        String username = "admin";
        Useraddress useraddress = new Useraddress(1,116,username,null,null,null,null,null,"1",new Date(),new Date());
        List<Users> usersList = new ArrayList<>();
        Users users = new Users(116,"admin",1,null,"123456",null,null,null,null,null,null,new Date(),new Date());
        usersList.add(users);
        List<Useraddress> useraddresses = new ArrayList<>();
        useraddresses.add(useraddress);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(addressMapper.selectByExample(any())).thenReturn(useraddresses);
        ResultVo resultVo = addressService.getAddressByName(username);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void setAddressTest(){
        AddressVo addressVo = new AddressVo(116,"admin",null,null,null,null,"1");
        List<Useraddress> useraddresses = new ArrayList<>();
        Mockito.when(addressMapper.selectDeaultAddress(anyInt())).thenReturn(useraddresses);
        Mockito.when(addressMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = addressService.setAddress(addressVo);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void setAddressTest2(){
        AddressVo addressVo = new AddressVo(116,"admin",null,null,null,null,"0");
        List<Useraddress> useraddresses = new ArrayList<>();
        Mockito.when(addressMapper.selectDeaultAddress(anyInt())).thenReturn(useraddresses);
        Mockito.when(addressMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = addressService.setAddress(addressVo);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void editAddressTest(){
        Useraddress useraddress = new Useraddress(1,116,"admin",null,null,null,null,null,"1",new Date(),new Date());
        List<Useraddress> list = new ArrayList<>();
        EntityHelper.initEntityNameMap(Useraddress.class, new Config());
        Mockito.when(addressMapper.selectByExample(any())).thenReturn(list);
        Mockito.when(addressMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = addressService.editAddress(useraddress);
        Assert.assertEquals(0,resultVo.getTotal());
        Useraddress useraddress2 = new Useraddress(1,116,"admin",null,null,null,null,null,"0",new Date(),new Date());
        ResultVo resultVo2 = addressService.editAddress(useraddress2);
        Assert.assertEquals(1,resultVo2.getTotal());
    }

    @Test
    public void editAddressTest2(){
        Useraddress useraddress = new Useraddress(1,116,"admin",null,null,null,null,null,"0",new Date(),new Date());
        List<Useraddress> list = new ArrayList<>();
        EntityHelper.initEntityNameMap(Useraddress.class, new Config());
        Mockito.when(addressMapper.selectByExample(any())).thenReturn(list);
        Mockito.when(addressMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = addressService.editAddress(useraddress);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void deleteAddressTest(){
        Mockito.when(addressMapper.deleteByPrimaryKey(anyInt())).thenReturn(1);
        ResultVo resultVo = addressService.deleteAddress(1);
        Assert.assertEquals(1,resultVo.getTotal());
    }
}
