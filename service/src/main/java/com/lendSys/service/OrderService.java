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
    public ResultVo getApprovalOrder(int current, int size);
    public ResultVo setRentOrder(Orders orders);
    public ResultVo approveRentOrder(String username);
    public ResultVo returnOrder(Orders orders);
    public ResultVo approveOrder(Orders orders);
    public ResultVo selectByStatus(String username,int status,int current, int size);

}
