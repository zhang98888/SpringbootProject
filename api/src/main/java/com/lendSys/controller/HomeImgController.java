package com.lendSys.controller;

import com.lendSys.service.homeImgService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/homeImg")
@CrossOrigin
@Api(value = "Home Img",tags = "Home Img for banner")
public class HomeImgController {
    @Resource
    private homeImgService homeImg;

    @GetMapping(value = "")
    public ResultVo getHomeImg() {
        return homeImg.getHomeImg();
    }
}
