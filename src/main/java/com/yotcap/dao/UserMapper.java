package com.yotcap.dao;

import com.yotcap.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public User getUserById(Integer id);

    /*根据用户名获取用户*/
    User getUserByUsername(String username);

    /*添加用户*/
    int addUser(@Param("user") User user);
}
