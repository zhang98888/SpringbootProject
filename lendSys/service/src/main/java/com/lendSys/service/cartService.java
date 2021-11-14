package com.lendSys.service;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Users;
import com.lendSys.vo.ResultVo;

public interface cartService {
    public ResultVo getAllCart(int current, int size, String username);
    public ResultVo removeCart(String id);
    public ResultVo addCart(Cart cart,String username);
    public ResultVo editCart(Cart cart);
}
