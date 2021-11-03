package com.lendSys.service;

import com.lendSys.entity.Category;
import com.lendSys.entity.Product;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.productVo;


public interface categoryService {

    public ResultVo getAllCategory(int current, int size );
    public ResultVo removeCategory(String id);
    public ResultVo addCategory(Category category);
    public ResultVo editCategory(Category category);
    public ResultVo searchCategory(int current, int size, CategoryVo categoryVo);
}
