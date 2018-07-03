package com.yotcap.controller;

import com.yotcap.dao.UserMapper;
import com.yotcap.pojo.User;
import com.yotcap.result.Result;
import com.yotcap.service.UserService;
import com.yotcap.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/test.do")
    @ResponseBody
    public Result<User> test(){

        User user = userService.getUserById(1);

        return Result.success(user);
    }


    /**
     * http://localhost:8080/user/login.do?username=kaibing&password=123456
     * @param loginVo
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public Result<String> login(@ModelAttribute("loginVo") LoginVo loginVo){


        return Result.success("请求成功:"+loginVo);
    }



}
