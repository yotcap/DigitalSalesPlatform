package com.yotcap.service.impl;

import com.yotcap.dao.UserMapper;
import com.yotcap.pojo.User;
import com.yotcap.result.CodeMsg;
import com.yotcap.result.Const;
import com.yotcap.result.Result;
import com.yotcap.service.UserService;
import com.yotcap.util.MD5Util;
import com.yotcap.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.UUID;

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

        String dbPass = user.getPassword();
        String formPass = MD5Util.formToDb(loginVo.getPassword(),user.getSalt());


        if (!Objects.equals(dbPass,formPass)){
            return Result.error(CodeMsg.PASSWORDERROR);
        }
//        密码置空
        user.setPassword("");
        user.setSalt("");
//        保存session
        session.setAttribute(Const.CURRENT_USER,user);

        return Result.success(user);
    }

    @Override
    public Result<String> logout(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if (user==null){
            return Result.error(CodeMsg.NOTLOGIN);
        }

        session.removeAttribute(Const.CURRENT_USER);

        return Result.success(CodeMsg.LOGOUTSUCCESS);
    }

    @Override
    public Result<User> register(User user) {

        User dbUser = userMapper.getUserByUsername(user.getUsername());
        if (dbUser!=null){

            return Result.error(CodeMsg.USERNAMEEXTIS);
        }

        if (Objects.equals("",user.getPassword())){
            return Result.error(CodeMsg.POSSCOULDNULL);
        }

        String salt = UUID.randomUUID().toString().substring(0,7);
        user.setPassword(MD5Util.formToDb(user.getPassword(),salt));
        user.setSalt(salt);
        int result = userMapper.addUser(user);

        if (result>0){
            user.setSalt("");
            user.setPassword("");
            return Result.success(user);
        }

        return Result.error(CodeMsg.REGISTERERROR);
    }


}
