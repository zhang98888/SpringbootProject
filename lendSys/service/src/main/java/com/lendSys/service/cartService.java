package com.lendSys.service;
import com.lendSys.entity.Cart;
import com.lendSys.entity.Users;
import com.lendSys.vo.ResultVo;

import java.util.List;

public interface cartService {
    public ResultVo getAllCart(int current, int size, String username);
    public ResultVo getAll(String username);
    public ResultVo removeCart(List<String> ids);
    public ResultVo addCart(Cart cart,String username);
    public ResultVo editCart(Cart cart);
}
