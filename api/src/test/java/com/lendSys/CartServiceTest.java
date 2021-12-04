package com.lendSys;

import com.lendSys.dao.CartMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Users;
import com.lendSys.service.cartService;
import com.lendSys.vo.ResultVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Rollback(true)
@MockBean(classes = {CartMapper.class,UsersMapper.class})
@RunWith(SpringRunner.class)
@Transactional
public class CartServiceTest {
    @Autowired
    private cartService carts;

    @MockBean(name = "cartMapper")
    private CartMapper cartMapper;

    @MockBean(name = "usersMapper")
    private UsersMapper usersMapper;

    @BeforeEach
    public void before(){
        carts = mock(cartService.class);
        cartMapper = mock(CartMapper.class);
        usersMapper = mock(UsersMapper.class);
    }
    @Test
    public void removeCartTest(){
        Cart cart = new Cart(1,1,116,2,new Date());
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        List<String> ids = new ArrayList<>();
        ids.add("1");
        Mockito.when(cartMapper.deleteByPrimaryKey(anyInt())).thenReturn(1);
        ResultVo resultVo = carts.removeCart(ids);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void getAllTest(){
        Cart cart = new Cart(1,1,116,2,new Date());
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        List<String> ids = new ArrayList<>();
        ids.add("1");
        Mockito.when(cartMapper.selectByName(anyString())).thenReturn(cartList);
        ResultVo resultVo = carts.getAll("admin");
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void addCartTest(){
        Cart cart = new Cart(1,1,116,2,new Date());
        List<Cart> cartList = new ArrayList<>();
        Users users = new Users(116,"admin",1,null,"123456",null,null,null,null,null,null,new Date(),new Date());
        cartList.add(cart);
        List<Users> usersList = new ArrayList<>();
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        Mockito.when(cartMapper.selectByProductAndName(anyInt(),any())).thenReturn(cartList);
        Mockito.when(cartMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = carts.addCart(cart,"admin");
        Assert.assertEquals(1,resultVo.getTotal());
    }


}
