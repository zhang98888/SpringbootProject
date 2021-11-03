package com.lendSys.service;
import com.lendSys.entity.ProductPicture;
import com.lendSys.vo.ResultVo;

public interface goodPicService {

    public ResultVo getGoodPic(int current, int size );
    public ResultVo removeGoodPic(String id);
    public ResultVo addGoodPic(ProductPicture productPicture);
    public ResultVo editGoodPic(ProductPicture productPicture);
    public ResultVo searchGoodPic(int current, int size, int id);
}
