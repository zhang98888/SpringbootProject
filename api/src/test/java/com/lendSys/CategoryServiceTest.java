package com.lendSys;

import com.lendSys.dao.CategoryMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Category;
import com.lendSys.entity.Users;
import com.lendSys.service.categoryService;
import com.lendSys.vo.ResultVo;
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
@MockBean(classes = {CategoryMapper.class})
@Transactional
public class CategoryServiceTest {

    @Resource
    private categoryService service;

    @MockBean(name = "categoryMapper")
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void before(){
        service = mock(categoryService.class);
        categoryMapper = mock(CategoryMapper.class);
    }

    @Test
    public void removeCategory(){
        Mockito.when(categoryMapper.deleteByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = service.removeCategory("1");
        Assert.assertEquals(1000,resultVo.getStatus());
    }

    @Test
    public void removeCategory2(){
        Mockito.when(categoryMapper.deleteByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = service.removeCategory("1");
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void addCategoryTest1(){
        Category category = new Category(1,"MMM",1,null,null);
        List<Category> categoryList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Category.class, new Config());
        Mockito.when(categoryMapper.selectByExample(any())).thenReturn(categoryList);
        Mockito.when(categoryMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = service.addCategory(category);
        Assert.assertEquals(1,resultVo.getTotal());
    }

    @Test
    public void addCategoryTest2(){
        Category category = new Category(1,"MMM",1,null,null);
        List<Category> categoryList = new ArrayList<>();
        EntityHelper.initEntityNameMap(Category.class, new Config());
        Mockito.when(categoryMapper.selectByExample(any())).thenReturn(categoryList);
        Mockito.when(categoryMapper.insert(any())).thenReturn(0);
        ResultVo resultVo = service.addCategory(category);
        Assert.assertEquals(0,resultVo.getTotal());
    }

    @Test
    public void addCategoryTest3(){
        Category category = new Category(1,"MMM",1,null,null);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        EntityHelper.initEntityNameMap(Category.class, new Config());
        Mockito.when(categoryMapper.selectByExample(any())).thenReturn(categoryList);
        Mockito.when(categoryMapper.insert(any())).thenReturn(1);
        ResultVo resultVo = service.addCategory(category);
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void editCategoryTest1(){
        Category category = new Category(1,"MMM",1,null,null);
        Mockito.when(categoryMapper.updateByPrimaryKey(any())).thenReturn(0);
        ResultVo resultVo = service.editCategory(category);
        Assert.assertEquals(1001,resultVo.getStatus());
    }

    @Test
    public void editCategoryTest2(){
        Category category = new Category(1,"MMM",1,null,null);
        Mockito.when(categoryMapper.updateByPrimaryKey(any())).thenReturn(1);
        ResultVo resultVo = service.editCategory(category);
        Assert.assertEquals(1000,resultVo.getStatus());
    }


}
