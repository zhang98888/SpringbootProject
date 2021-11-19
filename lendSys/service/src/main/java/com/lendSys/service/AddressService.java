package com.lendSys.service;

import com.lendSys.entity.Useraddress;
import com.lendSys.vo.AddressVo;
import com.lendSys.vo.ResultVo;

public interface AddressService {

    public ResultVo getAddressByName(String username);
    public ResultVo setAddress(AddressVo useraddress);
    public ResultVo editAddress(Useraddress useraddress);
    public ResultVo deleteAddress(int addrid);
}
