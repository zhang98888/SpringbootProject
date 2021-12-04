package com.lendSys;

import com.lendSys.dao.OrdersMapper;
import com.lendSys.dao.ProductMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Orders;
import com.lendSys.entity.Product;
import com.lendSys.entity.Users;
import com.lendSys.service.OrderService;
import com.lendSys.service.goodService;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {UsersMapper.class,OrdersMapper.class})
@Transactional
public class OrderServiceTest {
    @Resource
    private OrderService orderService;

    @MockBean(name = "ordersMapper")
    private OrdersMapper ordersMapper;

    @MockBean(name = "usersMapper")
    private UsersMapper usersMapper;


    @BeforeEach
    public void before(){
        orderService = mock(OrderService.class);
        ordersMapper = mock(OrdersMapper.class);
        usersMapper = mock(UsersMapper.class);
    }

    @Test
    public void addToOrderTest(){
        CartVo cartVo = new CartVo(1,1,1,1,new Date(),null);
        CartVo[] carts = new CartVo[1];
        carts[0] = cartVo;
        OrderVO orderVO = new OrderVO(new Date(),new Date(),12,1,1);
        Mockito.when(ordersMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = orderService.addToOrder(carts,orderVO);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void getReturnOrderTest(){
        Orders orders = new Orders(1,1,3,1,1,1,new Date(),null,12,null);
        List<Orders> list = new ArrayList<>();
        list.add(orders);
        Mockito.when(ordersMapper.selectbyUserName(anyString())).thenReturn(list);
        ResultVo resultVo = orderService.getReturnOrder("admin");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void setRentOrderTest(){
        Orders orders = new Orders(1,1,3,1,1,1,new Date(),null,12,null);
        Mockito.when(ordersMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = orderService.setRentOrder(orders);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void approveRentOrderTest1(){
        List<Users> usersList = new ArrayList<>();
        Users users = new Users(116,"admin",1,null,"123456",null,null,"111",null,null,null,null,null);
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        ResultVo resultVo = orderService.approveRentOrder("admin");
        Assert.assertEquals(1000,resultVo.getStatus());

    }

    @Test
    public void approveRentOrderTest2(){
        List<Users> usersList = new ArrayList<>();
        Users users = new Users(116,"admin",1,null,"123456",null,null,"1",null,null,null,null,null);
        usersList.add(users);
        EntityHelper.initEntityNameMap(Users.class, new Config());
        Mockito.when(usersMapper.selectByExample(any())).thenReturn(usersList);
        ResultVo resultVo = orderService.approveRentOrder("admin");
        Assert.assertEquals(1000,resultVo.getStatus());

    }

    @Test
    public void returnOrderTest(){
        Orders orders = new Orders();
        Mockito.when(ordersMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = orderService.returnOrder(orders);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void approveOrderTest(){
        Orders orders = new Orders();
        Mockito.when(ordersMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = orderService.approveOrder(orders);
        Assert.assertEquals(1000,resultVo.getStatus());
    }
}
