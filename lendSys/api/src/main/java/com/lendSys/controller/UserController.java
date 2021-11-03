package com.lendSys.controller;

import com.lendSys.entity.Users;
import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
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

    @PostMapping(value="/login")
    public ResultVo login(@RequestBody HashMap<String,String> map) throws Exception {
        String username = map.get("username");
        String pwd = map.get("pwd");
        System.out.println(username);
        System.out.println(pwd);

        return userService.checkLogin(username,pwd);
    }

    @PostMapping(value="/getUserInfo")
    public ResultVo getUserInfo(@RequestParam("token") String token){

        return userService.getUsrInfo(token);
    }
}
