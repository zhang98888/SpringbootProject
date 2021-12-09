package com.lendSys.controller;

import com.lendSys.service.homeImgService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/homeImg")
@CrossOrigin
@Api(value = "Home Img",tags = "Home Img for banner")
public class HomeImgController {
    @Resource
    private homeImgService homeImg;

    @ApiOperation(value  = "get home images",
            tags = "get home images", notes = "home images shown on main pages")
    @GetMapping(value = "")
    public ResultVo getHomeImg() {
        return homeImg.getHomeImg();
    }
}
