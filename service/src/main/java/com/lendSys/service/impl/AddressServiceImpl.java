package com.lendSys.service.impl;

import com.lendSys.dao.UseraddressMapper;
import com.lendSys.dao.UsersMapper;
import com.lendSys.entity.Useraddress;
import com.lendSys.entity.Users;
import com.lendSys.service.AddressService;
import com.lendSys.vo.AddressVo;
import com.lendSys.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Scope("singleton")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UseraddressMapper useraddressMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultVo getAddressByName(String username) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<Users> usersList = usersMapper.selectByExample(example);
        Example exampleAddr = new Example(Useraddress.class);
        Example.Criteria criteriaAddr = exampleAddr.createCriteria();
        criteriaAddr.andEqualTo("userid", usersList.get(0).getUserid());
        List list = useraddressMapper.selectByExample(exampleAddr);
        return new ResultVo(1000, "Success", list.size(), list);
    }

    @Override
    public ResultVo setAddress(AddressVo addressVo) {
        Useraddress useraddress = new Useraddress();
        useraddress.setAddress(addressVo.getAddress());
        useraddress.setDefaultAddress(addressVo.getDefaultAddress());
        useraddress.setUserid(addressVo.getUserid());
        useraddress.setCity(addressVo.getCity());
        useraddress.setPostcode(addressVo.getPostcode());
        useraddress.setReceiverName(addressVo.getReceiverName());
        useraddress.setReceiverMobile(addressVo.getReceiverMobile());
        useraddress.setCreateTime(new Date());
        useraddress.setUpdateTime(new Date());
        if (useraddress.getDefaultAddress().equals("1")) {
            List list = useraddressMapper.selectDeaultAddress(addressVo.getUserid());
            if (list.size() == 0) {
                useraddressMapper.insert(useraddress);
                return new ResultVo(1000, "Success", 1, useraddress);
            } else {
                return new ResultVo(1001, "You should only have  one default address", 0, null);
            }

        } else {
            useraddressMapper.insert(useraddress);
            return new ResultVo(1000, "Success", 1, useraddress);
        }
    }

    @Override
    public ResultVo editAddress(Useraddress useraddress) {
        if (useraddress.getDefaultAddress() == "1") {
            Example example = new Example(Useraddress.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("defaultAddress", "1");
            List<Useraddress> list = useraddressMapper.selectByExample(example);
            if (list.size() == 0 || (list.size() == 1 && list.get(0).getAddrid() == useraddress.getAddrid())) {
                useraddress.setUpdateTime(new Date());
                useraddressMapper.updateByPrimaryKey(useraddress);
                return new ResultVo(1000, "Success", 1, useraddress);
            } else {
                return new ResultVo(1001, "You should only have  one default address", 0, null);
            }

        } else {
            useraddress.setUpdateTime(new Date());
            useraddressMapper.updateByPrimaryKey(useraddress);
            return new ResultVo(1000, "Success", 1, useraddress);
        }
    }

    @Override
    public ResultVo deleteAddress(int addrid) {
        useraddressMapper.deleteByPrimaryKey(addrid);
        return new ResultVo(1000, "success", 1, null);
    }
}
