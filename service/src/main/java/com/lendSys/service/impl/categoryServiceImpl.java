package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.CategoryMapper;
import com.lendSys.entity.Category;
import com.lendSys.service.categoryService;
import com.lendSys.vo.CategoryVo;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class categoryServiceImpl implements categoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /*
    get all category
     */
    @Override
    public ResultVo getAllCategory(int current, int size) {
        PageHelper.startPage(current, size);
        List<Category> categoryList = categoryMapper.selectAll();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) categoryList;
        int total = categoryList.size();
        return new ResultVo(1000, "Success!", total, categoryList);
    }
    /*
    delete category
     */
    @Override
    public ResultVo removeCategory(String id) {
        Integer productId = Integer.parseInt(id);
        int num = categoryMapper.deleteByPrimaryKey(productId);
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);
    }
    /*
    add category to system. If the category exist, it will fail
     */
    @Transactional
    public ResultVo addCategory(Category category) {
        synchronized (this) {
            String categoryName = category.getCategoryName();
            Example example = new Example(Category.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("categoryName", categoryName);
            List<Category> categoryList = categoryMapper.selectByExample(example);
            // if not, need to save
            if (categoryList.size() == 0 && category.getCategoryId() != 0) {
                int check = categoryMapper.insert(category);
                if (check > 0) {
                    return new ResultVo(1000, "Success!", check, category);
                } else {
                    return new ResultVo(1001, "Fail to register", 0, null);
                }
            } else
                return new ResultVo(1001, "User has been register", 0, null);
        }
    }
    /*
    edit category
     */
    @Override
    public ResultVo editCategory(Category category) {
        int check = categoryMapper.updateByPrimaryKey(category);
        if(check == 0){
            return new ResultVo(1001, "Fail to update", 0, null);
        }else
            return new ResultVo(1000, "Success", 1, category);
    }
    /*
    search categoty by id and name.
    Search id firstly and then search name.
    if id and name are all null, it will alert
     */
    @Override
    public ResultVo searchCategory(int current, int size, CategoryVo categoryVo) {
        String categoryId = categoryVo.getCategoryId();
        String categoryName = categoryVo.getCategoryName();
        if(categoryId != null){
            int id = Integer.parseInt(categoryId);
            Category category = categoryMapper.selectByPrimaryKey(id);
            List<Category> lists = new ArrayList<>();
            lists.add(category);
            return new ResultVo(1000, "Success!", 1, lists);
        }else if(categoryName != null){
            PageHelper.startPage(current, size);
            List<Category> lists = null;
            Example example = new Example(Category.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("categoryName", categoryName);
            lists = categoryMapper.selectByExample(example);
            com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) lists;
            return new ResultVo(1000, "Success", lists.size(), lists);
        }
        return new ResultVo(1001,"The search Form is empty",0,null);
    }
}
