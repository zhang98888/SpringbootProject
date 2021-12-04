package com.lendSys;

import com.lendSys.service.*;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import com.lendSys.vo.productVo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private categoryService service;
    @Autowired
    private goodService Goodservice;
    @Autowired
    private cartService cartservice;
    @Autowired
    private goodPicService picService;
    @Autowired
    private OrderService orderService;

    @Test
    public void pageUserInfoTest(){
        ResultVo resultVo = adminService.pageUserInfo(1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void pageUserDetailInfoTest(){
        ResultVo resultVo = adminService.pageUserDetailInfo(1,1,null);
        Assert.assertEquals(1000,resultVo.getStatus());
        UserVo userVo = new UserVo("116","admin","2");
        ResultVo resultVo1 = adminService.pageUserDetailInfo(1,1,userVo);
        Assert.assertEquals(1000,resultVo1.getStatus());
        UserVo userVo2 = new UserVo(null,"admin",null);
        ResultVo resultVo2 = adminService.pageUserDetailInfo(1,1,userVo2);
        Assert.assertEquals(1000,resultVo2.getStatus());
    }

    @Test
    public void getAllCategoryTest(){
        ResultVo resultVo = service.getAllCategory(1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void searchCategoryTest(){
        CategoryVo categoryVo = new CategoryVo("1","laptops");
        ResultVo resultVo = service.searchCategory(1,1,categoryVo);
        Assert.assertEquals(1000,resultVo.getStatus());
        CategoryVo categoryVo2 = new CategoryVo(null,"laptops");
        ResultVo resultVo2 = service.searchCategory(1,1,categoryVo2);
        Assert.assertEquals(1000,resultVo2.getStatus());
    }

    @Test
    public void getAllGoodsTest(){
        ResultVo resultVo = Goodservice.getAllGoods(1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void searchGoodsTest(){
        productVo productVo = new productVo("1","product");
        ResultVo resultVo = Goodservice.searchGoods(1,1,productVo);
        productVo productVo2 = new productVo(null,"product");
        ResultVo resultVo2 = Goodservice.searchGoods(1,1,productVo2);
        Assert.assertEquals(1000,resultVo.getStatus());
        Assert.assertEquals(1000,resultVo2.getStatus());
    }

    @Test
    public void getAllCartTest(){
        ResultVo resultVo2 = cartservice.getAllCart(1,1,"admin");
        Assert.assertEquals(1000,resultVo2.getStatus());
    }

    @Test
    public void getGoodPicTest(){
        ResultVo resultVo2 =picService.getGoodPic(1,1);
        Assert.assertEquals(1000,resultVo2.getStatus());
    }

    @Test
    public void getProductPicTest(){
        ResultVo resultVo = picService.getProductPic(1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void gatCategoryPicTest(){
        ResultVo resultVo =picService.gatCategoryPic("1","1","1");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void getAllOrderTest(){
        ResultVo resultVo = orderService.getAllOrder("admin",1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void getApprovalOrderTest(){
        ResultVo resultVo = orderService.getApprovalOrder(1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void selectByStatus(){
        ResultVo resultVo = orderService.selectByStatus("admin",1,1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }



}
