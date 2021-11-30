package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.OrdersMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Orders;
import com.lendSys.entity.Users;
import com.lendSys.service.OrderService;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultVo getAllOrder(String username, int current, int size) {
        PageHelper.startPage(current, size);
        List<Orders> list = ordersMapper.selectbyUserName(username);
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) list;
        return new ResultVo(1000, "success!", list.size(), list);
    }

    @Override
    public ResultVo addToOrder(CartVo[] carts, OrderVO orderVO) {
        for (CartVo cart : carts) {
            Orders order = new Orders();
            order.setUserId(cart.getUserId());
            order.setCartNum(cart.getCartNum());
            order.setProductId(cart.getProductid());
            order.setProductName(cart.getProductName());
            order.setOrderStatus(1);
            order.setOrderRemark(orderVO.getOrderRemark());
            order.setStartDate(new Date());
            order.setLength(orderVO.getBorrowlength());
            ordersMapper.insert(order);
        }
        return new ResultVo(1000, "Success!", 1, null);
    }

    @Override
    public ResultVo getReturnOrder(String username){
        List<Orders> list = ordersMapper.selectbyUserName(username);
        List<Orders> newList = new ArrayList<>();
        for(Orders order: list){
            if(order.getOrderStatus() == 3){
                newList.add(order);
            }
        }
        return new ResultVo(1000,"success",newList.size(),newList);
    }

    @Override
    public ResultVo getApprovalOrder(int current, int size){
        PageHelper.startPage(current, size,true);
        List<Orders> list = ordersMapper.waitForCollect();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) list;
        return new ResultVo(1000,"Success",list.size(),list);
    }

    @Override
    public ResultVo setRentOrder(Orders orders){
        int len = orders.getLength();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, len);
        Date enddate = cal.getTime();
        orders.setEndDate(enddate);
        orders.setOrderStatus(3);
        ordersMapper.updateByPrimaryKey(orders);
        return new ResultVo(1000,"success",1, orders);
    }

    @Override
    // The orders which will be shown on manager's pages
    public ResultVo approveRentOrder(String username){
        Example example= new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",  username);
        List<Users> usersList = usersMapper.selectByExample(example);
        Users user = usersList.get(0);
        if(user.getUserLevel().equals("1")){
            List<Orders> newList = ordersMapper.getAdminApproval();
            return new ResultVo(1000,"Success",newList.size(),newList);
        } else{
            List<Orders> list = ordersMapper.waitForApproval(username);
            return new ResultVo(1000,"Success",list.size(),list);
        }
    }
    @Override
    public ResultVo returnOrder(Orders orders){
        orders.setOrderStatus(4);
        ordersMapper.updateByPrimaryKey(orders);
        return new ResultVo(1000,"Success",1,orders);
    }
    @Override
    public ResultVo approveOrder(Orders orders){
        orders.setOrderStatus(2);
        ordersMapper.updateByPrimaryKey(orders);
        return new ResultVo(1000,"Success",1,orders);
    }

    @Override
    public ResultVo selectByStatus(String username,int status,int current, int size){
        PageHelper.startPage(current, size);
        List<Orders> list = ordersMapper.selectByStatus(username,status);
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) list;
        return new ResultVo(1000,"Success",list.size(),list);
    };

}
