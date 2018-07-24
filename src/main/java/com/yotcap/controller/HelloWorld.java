package com.yotcap.controller;



import com.yotcap.pojo.Test;
import com.yotcap.result.Result;
import com.yotcap.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.yotcap.result.Result.success;

@Controller
public class HelloWorld {

    @Autowired
    private TestService testService;

    @Autowired
    protected RedisTemplate<String,String> redisTemplate ;


    @Value("#{appProperties['session.key.user']}")
    public String userKey;

    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/userKey.do")
    @ResponseBody
    public Result<String> userKey(){

        return success(userKey);
    }

    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/req.do")
    @ResponseBody
    public Result<String> userKey(HttpServletRequest request){
        System.out.println(request.getServletPath());//req.do
        System.out.println(request.getScheme());//http
        System.out.println(request.getServerName());//localhost
        System.out.println(request.getServerPort());//8080
        System.out.println(request.getAuthType());//null
        System.out.println(request.getContextPath());
        System.out.println(request.getCookies());//[Ljavax.servlet.http.Cookie;@183a9476
        System.out.println(request.getHeaderNames());//org.apache.tomcat.util.http.NamesEnumerator@4ea09b16
        System.out.println(request.getMethod());//GET
        System.out.println(request.getRequestURL());//http://localhost:8080/req.do
        return success("成功");
    }

    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/hello.do")
    @ResponseBody
    public Result<Test> helloWorld(){

        Test  test = testService.queryByUserId(1);
        return success(test);
    }
    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/redis.do")
    @ResponseBody
    public Result<String> redis(){
        redisTemplate.opsForValue().set("name","kaibing");
        String str =  redisTemplate.opsForValue().get("name");
        return Result.success(str);
    }





}
