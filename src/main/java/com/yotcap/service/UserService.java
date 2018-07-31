package com.yotcap.service;

import com.yotcap.pojo.User;
import com.yotcap.result.Result;
import com.yotcap.vo.LoginVo;

import javax.servlet.http.HttpSession;

public interface UserService {

    public User getUserById(Integer id);

    Result<User> login(LoginVo loginVo, HttpSession session);

    Result<String> logout(HttpSession session);

    Result<User> register(User user);
}
