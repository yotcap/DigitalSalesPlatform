package com.yotcap.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotcap.dao.UserMapper;
import com.yotcap.pojo.User;
import com.yotcap.result.CodeMsg;
import com.yotcap.result.Const;
import com.yotcap.result.Result;
import com.yotcap.service.UserService;
import com.yotcap.util.SendMessage;
import com.yotcap.vo.LoginVo;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
    public Result<User> login(@ModelAttribute("loginVo") LoginVo loginVo,
                              HttpSession session){

//        System.out.println(loginVo);
        return userService.login(loginVo,session);
    }

    /**
     * http://localhost:8080/user/login.do?username=kaibing&password=123456
     * @param session
     * @return
     */
    @RequestMapping("/getUser.do")
    @ResponseBody
    public Result<User> getUser(HttpSession session){


        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return Result.error(CodeMsg.NOTLOGIN);
        }
//        System.out.println(loginVo);
        return Result.success(user);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout.do")
    @ResponseBody
    public Result<String> logout(HttpSession session){
        return userService.logout(session);
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register.do")
    @ResponseBody
    public Result<User> register(@ModelAttribute("user") User user,
                                 String verCode,
                                 MultipartFile picture,
                                 HttpSession session){



        if (picture!=null){

            String pathval = session.getServletContext().getRealPath("/static/upImage");
            String newFileName = UUID.randomUUID().toString();
            File newFile = new File(pathval+"/"+newFileName);
            try {
                picture.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setImage("/static/upImage/"+newFileName);

        }


        String msgCode = (String) session.getAttribute(Const.VERCODE);

        if (!Objects.equals(msgCode,verCode)){
//            验证失败
            return Result.error(CodeMsg.MSGERROR);
        }

        return userService.register(user);
    }

    @RequestMapping("/verification.do")
    @ResponseBody
    public Result<String> verification(String mobile,HttpSession session){
        try {
            HttpEntity httpEntity = SendMessage.sendMsg(mobile);
            String response= EntityUtils.toString(httpEntity,"utf-8");
//            String code= JSON.parseObject(response).getString("code");
            JSONObject jsonObject = JSON.parseObject(response);
            if (jsonObject.get("code").toString().equals("200")){
                System.out.println(jsonObject.get("obj").toString());
                session.setAttribute(Const.VERCODE,jsonObject.get("obj").toString());
            }
            return Result.success(jsonObject.get("obj").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.MSGSENDERROR);
        }

    }

}
