package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.OrdersMapper;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Orders;
import com.lendSys.service.OrderService;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

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
}
