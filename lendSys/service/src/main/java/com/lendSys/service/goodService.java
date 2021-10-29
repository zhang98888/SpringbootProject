package com.lendSys.service;

import com.lendSys.entity.Product;
import com.lendSys.vo.ResultVo;

public interface goodService {

    public ResultVo getAllGoods( int current, int size );
    public ResultVo removeGoods(String id);
    public ResultVo addGoods(Product product);
    public ResultVo editGoods(Product product);
}
