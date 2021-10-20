package com.lendSys.controller;

import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(value = "User Management",tags = "User Management")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value="/login")
    public ResultVo login(@RequestParam(value = "userName") String name,
                          @RequestParam(value = "pwd") String pwd){
        return userService.checkLogin(name,pwd);
    }

    @PostMapping(value="/regist")
    public ResultVo register(int userId,String userName, String pwd){
        return userService.userRegist(userId,userName,pwd);
    }
}
