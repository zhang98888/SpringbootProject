package com.lendSys.controller;

import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public ResultVo login(String name, String pwd){

        return userService.checkLogin(name,pwd);
    }
}
