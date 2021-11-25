package com.lendSys.controller;

import com.lendSys.entity.Useraddress;
import com.lendSys.service.AddressService;
import com.lendSys.vo.AddressVo;
import com.lendSys.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
@Api(value = "User Address Management",tags = "User Address Management")
@CrossOrigin
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping(value = "/")
    public ResultVo getAddressByName(@RequestParam("username") String username){
        return addressService.getAddressByName(username);
    }

    @PostMapping(value = "/setAddress")
    public ResultVo setAddress(@RequestBody AddressVo addressVo){
        return addressService.setAddress(addressVo);
    }

    @PostMapping(value = "/editAddress")
    public ResultVo editAddress(@RequestBody Useraddress useraddress){
        return addressService.editAddress(useraddress);
    }

    @DeleteMapping(value = "/deleteAddress/{addrid}")
    public ResultVo deleteAddress(@PathVariable int addrid){
        return addressService.deleteAddress(addrid);
    }
}
