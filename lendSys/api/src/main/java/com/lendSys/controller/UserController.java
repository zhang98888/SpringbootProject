package com.lendSys.controller;

import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
@Api(value = "User Management",tags = "User Management")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value="/login")
    public ResultVo login(String userid, String pwd){
        return userService.checkLogin(userid,pwd);
    }
}
