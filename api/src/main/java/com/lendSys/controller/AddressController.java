package com.lendSys.controller;

import com.lendSys.entity.Useraddress;
import com.lendSys.service.AddressService;
import com.lendSys.vo.AddressVo;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
@Api(value = "User Address Management",tags = "User Address Management")
@CrossOrigin
public class AddressController {
    @Resource
    private AddressService addressService;

    @ApiOperation(value  = "get user address", notes = "get user address")
    @GetMapping(value = "/")
    public ResultVo getAddressByName(@RequestParam("username") String username){
        return addressService.getAddressByName(username);
    }

    @ApiOperation(value  = "add user address", notes = "add user address")
    @PostMapping(value = "/setAddress")
    public ResultVo setAddress(@RequestBody AddressVo addressVo){
        return addressService.setAddress(addressVo);
    }

    @ApiOperation(value  = "edit user address", notes = "edit user address")
    @PostMapping(value = "/editAddress")
    public ResultVo editAddress(@RequestBody Useraddress useraddress){
        return addressService.editAddress(useraddress);
    }

    @ApiOperation(value  = "delete user address", notes = "delete user address")
    @DeleteMapping(value = "/deleteAddress/{addrid}")
    public ResultVo deleteAddress(@PathVariable int addrid){
        return addressService.deleteAddress(addrid);
    }
}
