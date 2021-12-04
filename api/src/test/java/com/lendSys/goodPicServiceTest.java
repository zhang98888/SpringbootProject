package com.lendSys;

import com.lendSys.dao.ProductMapper;
import com.lendSys.dao.ProductPictureMapper;
import com.lendSys.dao.UseraddressMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.ProductPicture;
import com.lendSys.service.AddressService;
import com.lendSys.service.goodPicService;
import com.lendSys.vo.ResultVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {ProductPictureMapper.class, ProductMapper.class})
@Transactional
public class goodPicServiceTest {
    @Autowired
    private goodPicService picService;
    @MockBean(name = "pictureMapper")
    private ProductPictureMapper pictureMapper;
    @MockBean(name = "productMapper")
    private ProductMapper productMapper;

    @BeforeEach
    public void before(){
        picService = mock(goodPicService.class);
        pictureMapper = mock(ProductPictureMapper.class);
        productMapper = mock(ProductMapper.class);
    }

    @Test
    public void removeGoodPicTest1(){
        Mockito.when(pictureMapper.deleteByPrimaryKey(anyInt())).thenReturn(1);
        ResultVo resultVo = picService.removeGoodPic("1");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void removeGoodPicTest2(){
        Mockito.when(pictureMapper.deleteByPrimaryKey(anyInt())).thenReturn(0);
        ResultVo resultVo = picService.removeGoodPic("1");
        Assert.assertEquals(1001,resultVo.getStatus());
    }


    @Test
    public void addGoodPicTest1(){
        ProductPicture productPicture = new ProductPicture();
        Mockito.when(pictureMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = picService.addGoodPic(productPicture);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void addGoodPicTest2(){
        ProductPicture productPicture = new ProductPicture();
        Mockito.when(pictureMapper.insert(any())).thenReturn(0);
        ResultVo resultVo = picService.addGoodPic(productPicture);
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void editGoodPicTest1(){
        ProductPicture productPicture = new ProductPicture();
        Mockito.when(pictureMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = picService.editGoodPic(productPicture);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void editGoodPicTest2(){
        ProductPicture productPicture = new ProductPicture();
        Mockito.when(pictureMapper.updateByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = picService.editGoodPic(productPicture);
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void searchGoodPicTest(){
        ProductPicture productPicture = new ProductPicture();
        Mockito.when(pictureMapper.selectByPrimaryKey(anyInt())).thenReturn(productPicture);
        ResultVo resultVo = picService.searchGoodPic(1,1,1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void getAdvanceGoodPicTest(){
        List<ProductPicture> lists = new ArrayList<>();
        Mockito.when(pictureMapper.selectAll()).thenReturn(lists);
        ResultVo resultVo = picService.getAdvanceGoodPic(1);
        Assert.assertEquals(1000,resultVo.getStatus());
    }
}
