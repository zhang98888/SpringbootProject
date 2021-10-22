package com.lendSys.controller;

import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(value = "Admin Management",tags = "Admin Management")
@CrossOrigin
public class AdminController {
    @Resource
    private AdminService adminService;

    @GetMapping(value="/home")
    public List<Users> userInfo(){
        return adminService.userInfo();
    }

    @PostMapping(value="/regist")
    public ResultVo register(int userId,String userName, String pwd){
        return adminService.userRegist(userId,userName,pwd);
    }
}
