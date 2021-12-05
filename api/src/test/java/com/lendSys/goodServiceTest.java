package com.lendSys;

import com.lendSys.dao.CategoryMapper;
import com.lendSys.dao.ProductMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Category;
import com.lendSys.entity.Product;
import com.lendSys.service.categoryService;
import com.lendSys.service.goodService;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.detailProductVo;
import com.sun.source.tree.ModuleTree;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {ProductMapper.class})
@Transactional
public class goodServiceTest {

    @Resource
    private goodService service;

    @MockBean(name = "productMapper")
    private ProductMapper productMapper;

    @BeforeEach
    public void before(){
        service = mock(goodService.class);
        productMapper = mock(ProductMapper.class);
    }

    @Test
    public void getDetailGoodsTest(){
        List<detailProductVo> productList = new ArrayList<>();
        Mockito.when(productMapper.getDetailProduct(1)).thenReturn(productList);
        ResultVo resultVo = service.getDetailGoods("1");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void removeGoodsTest(){
        Mockito.when(productMapper.deleteByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = service.removeGoods("1");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void removeGoodsTest2(){
        Mockito.when(productMapper.deleteByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = service.removeGoods("1");
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void addGoodsTest(){
        Product product = new Product(1,1,"product",100,1,null,null);
        List<Product> goodsList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Product.class, new Config());
        Mockito.when(productMapper.selectByExample(any())).thenReturn(goodsList);
        Mockito.when(productMapper.insert(product)).thenReturn(1);
        ResultVo resultVo = service.addGoods(product);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void addGoodsTest2(){
        Product product = new Product(1,1,"product",100,1,null,null);
        List<Product> goodsList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Product.class, new Config());
        Mockito.when(productMapper.selectByExample(any())).thenReturn(goodsList);
        Mockito.when(productMapper.insert(product)).thenReturn(0);
        ResultVo resultVo = service.addGoods(product);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void addGoodsTest3(){
        Product product = new Product(1,0,"product",100,1,null,null);
        List<Product> goodsList = new ArrayList<>();
        goodsList.add(product);
        EntityHelper.initEntityNameMap(Product.class, new Config());
        Mockito.when(productMapper.selectByExample(any())).thenReturn(goodsList);
        Mockito.when(productMapper.insert(product)).thenReturn(0);
        ResultVo resultVo = service.addGoods(product);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void addGoodsTest4(){
        Product product = new Product(1,1,"product",100,1,null,null);
        List<Product> goodsList = new ArrayList<>();
        goodsList.add(product);
        EntityHelper.initEntityNameMap(Product.class, new Config());
        Mockito.when(productMapper.selectByExample(any())).thenReturn(goodsList);
        Mockito.when(productMapper.insert(product)).thenReturn(0);
        ResultVo resultVo = service.addGoods(product);
        Assert.assertEquals(0,resultVo.getTotal());
    }



    @Test
    public void editGoodsTest(){
        Product product = new Product(1,1,"product",100,1,null,null);
        Mockito.when(productMapper.updateByPrimaryKey(product)).thenReturn(1);
        ResultVo resultVo = service.editGoods(product);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void editGoodsTest2(){
        Product product = new Product(1,1,"product",100,1,null,null);
        Mockito.when(productMapper.updateByPrimaryKey(product)).thenReturn(0);
        ResultVo resultVo = service.editGoods(product);
        Assert.assertEquals(0,resultVo.getTotal());
    }
}
