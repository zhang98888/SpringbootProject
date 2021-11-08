package com.lendSys.service.impl;

import com.lendSys.dao.HomeImgMapper;
import com.lendSys.entity.HomeImg;
import com.lendSys.service.homeImgService;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class homeImgServiceImpl implements homeImgService {
    @Autowired
    private HomeImgMapper homeImgMapper;

    @Override
    public ResultVo getHomeImg() {
       List<HomeImg> list = homeImgMapper.selectAll();
        return new ResultVo(1000,"success",list.size(),list);
    }
}
