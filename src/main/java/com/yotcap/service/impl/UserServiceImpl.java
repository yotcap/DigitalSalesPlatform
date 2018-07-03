package com.yotcap.service.impl;

import com.yotcap.dao.UserMapper;
import com.yotcap.pojo.User;
import com.yotcap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
