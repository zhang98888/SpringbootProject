package com.lendSys;

import com.lendSys.dao.HomeImgMapper;
import com.lendSys.dao.UseraddressMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.HomeImg;
import com.lendSys.service.AddressService;
import com.lendSys.service.homeImgService;
import com.lendSys.vo.ResultVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
@Rollback(true)
@RunWith(SpringRunner.class)
@MockBean(classes = {HomeImgMapper.class})
@Transactional
public class homeImgServiceTest {
    @Autowired
    private homeImgService homeimgService;

    @MockBean(name = "addressMapper")
    private HomeImgMapper homeImgMapper;


    @BeforeEach
    public void before(){
        homeimgService = mock(homeImgService.class);
        homeImgMapper = mock(HomeImgMapper.class);
    }

    @Test
    public void getHomeImgTest(){
        List<HomeImg> list = new ArrayList<>();
        Mockito.when(homeImgMapper.selectAll()).thenReturn(list);
        ResultVo resultVo = homeimgService.getHomeImg();
        Assert.assertEquals(1000,resultVo.getStatus());
    }



}
