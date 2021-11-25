package com.lendSys.dao;

import com.lendSys.entity.Orders;
import com.lendSys.generalDao.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersMapper extends GeneralDao<Orders> {
    public List<Orders> selectbyUserName(String username);

    public List<Orders> waitForCollect();

    public List<Orders> waitForApproval(String username);

    public List<Orders> selectByStatus(String username,int orderStatus);
}