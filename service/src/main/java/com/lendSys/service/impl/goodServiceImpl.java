package com.lendSys.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.lendSys.dao.ProductMapper;
import com.lendSys.entity.Product;
import com.lendSys.entity.Users;
import com.lendSys.service.goodService;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.detailProductVo;
import com.lendSys.vo.productVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class goodServiceImpl implements goodService {
    @Autowired
    private ProductMapper productMapper;
    /*
    get all goods and shown by pages.
     */
    @Override
    public ResultVo getAllGoods(int current, int size) {
        PageHelper.startPage(current, size);
        List<Product> goodsList = productMapper.selectAll();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) goodsList;
        int total = goodsList.size();
        return new ResultVo(1000, "Success!", total, goodsList);
    }
    /*
    get good detail information
     */
    @Override
    public ResultVo getDetailGoods( String id) {
        int productId = Integer.parseInt(id);
        List<detailProductVo> goodsList = productMapper.getDetailProduct(productId);
        int total = goodsList.size();
        return new ResultVo(1000, "Success!", total, goodsList);
    }
    /*
    remove goods
     */
    @Override
    public ResultVo removeGoods(String id) {
        Integer productId = Integer.parseInt(id);
        int num = productMapper.deleteByPrimaryKey(productId);
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);
    }
    /*
    add goods
    it should have the category number.
     */
    @Transactional
    public ResultVo addGoods(Product product) {
        synchronized (this) {
            String productName = product.getProductName();
            Example example = new Example(Product.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("productName", product.getProductName());
            List<Product> goodsList = productMapper.selectByExample(example);
            // if not, need to save
            if (goodsList.size() == 0 && product.getCategoryId() != 0) {
                int check = productMapper.insert(product);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", check, product);
                } else {
                    return new ResultVo(1001, "Fail to register", 0, null);
                }
            } else if (product.getCategoryId() == 0) {
                return new ResultVo(1001, "No department", 0, null);
            } else
                return new ResultVo(1001, "User has been register", 0, null);
        }
    }
    /*
    eidt goods
     */
    @Override
    public ResultVo editGoods(Product product) {
        int check = productMapper.updateByPrimaryKey(product);
        if(check == 0){
            return new ResultVo(1001, "Fail to update", 0, null);
        }else
            return new ResultVo(1000, "Success", 1, product);
    }
    /*
    search goods
     */
    @Override
    public ResultVo searchGoods(int current, int size, productVo productVo){
        String productId = productVo.getProductId();
        String productName = productVo.getProductName();
        if(!StringUtils.isEmpty(productId)){
            int id = Integer.parseInt(productId);
            Product product = productMapper.selectByPrimaryKey(id);
            List<Product> lists = new ArrayList<>();
            lists.add(product);
            return new ResultVo(1000, "Success!", 1, lists);
        }else if(!StringUtils.isEmpty(productName)){
            PageHelper.startPage(current, size);
            List<Product> productList = null;
            Example example = new Example(Product.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("productName", productName);
            productList = productMapper.selectByExample(example);
            com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) productList;
            return new ResultVo(1000, "Success", productList.size(), productList);
        }
        return new ResultVo(1001,"The search Form is empty",0,null);
    }
}
