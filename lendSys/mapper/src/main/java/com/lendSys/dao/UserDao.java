package com.lendSys.dao;

import com.lendSys.entity.User;

public interface UserDao {

    public User searchUserByName(String name);

}
