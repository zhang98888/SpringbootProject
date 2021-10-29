package com.lendSys.controller;

import com.lendSys.entity.Users;
import com.lendSys.service.AdminService;
import com.lendSys.service.UserService;
import com.lendSys.vo.ResultVo;
import com.lendSys.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(value = "Admin Management",tags = "Admin Management")
@CrossOrigin
public class AdminController {
    @Resource
    private AdminService adminService;

    @GetMapping(value="/userInfo")
    public ResultVo userInfo(){
        return adminService.userInfo();
    }

    @PostMapping(value="/addUserInfo")
    public ResultVo addUserInfo(@RequestBody Users users){
        return adminService.addUserInfo(users);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResultVo deleteUser(@PathVariable String id){
        return adminService.userRemove(id);
    }

    @GetMapping(value = "/pageUser/{current}/{size}")
    public ResultVo getPageUsers(@PathVariable String current,
                                 @PathVariable String size){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return adminService.pageUserInfo(curr,s);
    }

    @PostMapping(value = "/pageSearchUser/{current}/{size}")
    public ResultVo getPageUsersAdvance(@PathVariable String current,
                                        @PathVariable String size,
                                        @RequestBody(required = false) UserVo userVo){
        int curr = Integer.parseInt(current);
        int s = Integer.parseInt(size);
        return adminService.pageUserDetailInfo(curr,s,userVo);
    }

    @PostMapping(value = "/editUserInfo")
    public ResultVo editUserInfo(@RequestBody Users users){
        return adminService.editUserInfo(users);
    }
}
