package com.lendSys.service.impl;

import com.lendSys.dao.HomeImgMapper;
import com.lendSys.entity.HomeImg;
import com.lendSys.service.homeImg;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class homeImgImpl implements homeImg {
    @Autowired
    private HomeImgMapper homeImgMapper;

    @Override
    public ResultVo getHomeImg() {
       List<HomeImg> list = homeImgMapper.selectAll();
        return new ResultVo(1000,"success",list.size(),list);
    }
}
