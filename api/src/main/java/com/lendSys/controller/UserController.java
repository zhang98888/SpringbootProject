package com.lendSys.controller;

import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/")
@Api(value = "User Management",tags = "User Management")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value  = "log in",
            tags = "log in", notes = "log in ")
    @PostMapping(value="/login")
    public ResultVo login(@RequestBody HashMap<String,String> map) throws Exception {
        String username = map.get("username");
        String pwd = map.get("pwd");
        System.out.println(username);
        System.out.println(pwd);

        return userService.checkLogin(username,pwd);
    }

    @ApiOperation(value  = "get user information",
            tags = "get user information", notes = "get user information by username")
    @PostMapping(value="/getUserInfo")
    public ResultVo getUserInfo(@RequestParam("username") String username){

        return userService.getUsrInfo(username);
    }
}
