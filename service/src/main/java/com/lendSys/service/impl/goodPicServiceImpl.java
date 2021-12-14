package com.lendSys.service.impl;

import com.github.pagehelper.PageHelper;
import com.lendSys.dao.ProductMapper;
import com.lendSys.dao.ProductPictureMapper;
import com.lendSys.entity.Category;
import com.lendSys.entity.Product;
import com.lendSys.entity.ProductPicture;
import com.lendSys.service.goodPicService;
import com.lendSys.vo.CategoryPicVo;
import com.lendSys.vo.PicInfoVo;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class goodPicServiceImpl implements goodPicService {
    @Autowired
    private ProductPictureMapper pictureMapper;
    @Autowired
    private ProductMapper productMapper;
    /*
    get all good pictures
     */
    @Override
    public ResultVo getGoodPic(int current, int size) {
        PageHelper.startPage(current, size);
        List<ProductPicture> list = pictureMapper.selectAll();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) list;
        int total = list.size();
        return new ResultVo(1000, "Success!", total, list);
    }
    /*
    get pictures by category
     */
    @Override
    public ResultVo getProductPic(int current, int size) {
        PageHelper.startPage(current, size);
        List<CategoryPicVo> list = pictureMapper.productPic();
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) list;
        int total = list.size();
        return new ResultVo(1000, "Success!", total, list);
    }
    /*
    delete good pictures
     */
    @Override
    public ResultVo removeGoodPic(String id) {
        Integer pictureId = Integer.parseInt(id);
        int num = pictureMapper.deleteByPrimaryKey(pictureId);
        if (num > 0) {
            return new ResultVo(1000, "Delete Success!", num, null);
        } else
            return new ResultVo(1001, "Fail to delete!", 0, null);

    }
    /*
    add goods pictures
     */
    @Transactional
    public ResultVo addGoodPic(ProductPicture productPicture) {
        synchronized (this) {
//            int pictureId = productPicture.getPictureId();
//            if (pictureId == 0) {
//                return new ResultVo(1001, "Need to input pictureId", 0, null);
//            }
//            ProductPicture picture = pictureMapper.selectByPrimaryKey(pictureId);
//            // if not, need to save
//            if (picture == null) {
            productPicture.setSort(0);
            int check = pictureMapper.insert(productPicture);
            if (check > 0) {
                return new ResultVo(1000, "Success!", check, productPicture);
            } else {
                return new ResultVo(1001, "Fail to register", 0, null);
            }
        }
    }
    /*
    edit goods pictures
     */
    @Override
    public ResultVo editGoodPic(ProductPicture productPicture) {
        int check = pictureMapper.updateByPrimaryKey(productPicture);
        if (check == 0) {
            return new ResultVo(1001, "Fail to update", 0, null);
        } else
            return new ResultVo(1000, "Success", 1, productPicture);
    }
    /*
    search good picture by id
    if id is null, it will alert
     */
    @Override
    public ResultVo searchGoodPic(int current, int size, int id) {
        if(id != 0){
            ProductPicture picture = pictureMapper.selectByPrimaryKey(id);
            List<ProductPicture> lists = new ArrayList<>();
            lists.add(picture);
            return new ResultVo(1000, "Success!", 1, lists);
        }else
            return new ResultVo(1001, "The search Form is empty", 0, null);
    }

    @Override
    public ResultVo getAdvanceGoodPic(int num) {
        List<ProductPicture> lists = pictureMapper.selectAll();
        List<PicInfoVo> list = new ArrayList<>();
        for (ProductPicture pic : lists) {
            if (pic.getSort() == num) {
                Product product = productMapper.selectByPrimaryKey(pic.getProductId());
                PicInfoVo pices = new PicInfoVo(product.getProductName(), product.getRentNum(), pic.getUrl(), pic.getProductId());
                list.add(pices);
            }
        }
        return new ResultVo(1000, "Success!", 1, list);
    }

    @Override
    public ResultVo gatCategoryPic(String categoryId,String current,String size){
        int id = Integer.parseInt(categoryId);
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        PageHelper.startPage(curr, s);
        List<CategoryPicVo> productList = pictureMapper.selectCategoryPic(id);
        com.github.pagehelper.Page listWithPage = (com.github.pagehelper.Page) productList;
        return new ResultVo(1000,"success!",productList.size(),productList);
    }
}
