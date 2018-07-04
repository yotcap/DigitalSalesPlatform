package com.yotcap.service.impl;

import com.yotcap.dao.UserMapper;
import com.yotcap.pojo.User;
import com.yotcap.result.CodeMsg;
import com.yotcap.result.Const;
import com.yotcap.result.Result;
import com.yotcap.service.UserService;
import com.yotcap.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Result<User> login(LoginVo loginVo, HttpSession session) {
        //根据Vo查找用户
        User user = userMapper.getUserByUsername(loginVo.getUsername());
        if (user == null){
            return Result.error(CodeMsg.USERNOTFOUND);
        }
        if (!user.getPassword().equals(loginVo.getPassword())){
            return Result.error(CodeMsg.PASSWORDERROR);
        }
//        密码置空
        user.setPassword("");
//        保存session
        session.setAttribute(Const.CURRENT_USER,user);

        return Result.success(user);
    }
}
