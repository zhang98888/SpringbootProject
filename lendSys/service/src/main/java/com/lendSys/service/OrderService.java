package com.lendSys.service;

import com.lendSys.entity.Cart;
import com.lendSys.entity.Orders;
import com.lendSys.vo.CartVo;
import com.lendSys.vo.OrderVO;
import com.lendSys.vo.ResultVo;


public interface OrderService {
    public ResultVo getAllOrder(String username,int current, int page);
    public ResultVo addToOrder(CartVo[] cart, OrderVO orderVO);
    public ResultVo getReturnOrder(String username);
}
