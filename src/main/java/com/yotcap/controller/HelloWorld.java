package com.yotcap.controller;



import com.alibaba.fastjson.JSONObject;
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

import java.util.Map;

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
        System.out.println(request.getContextPath());//返回站点的根路径
        System.out.println(request.getCookies());//[Ljavax.servlet.http.Cookie;@183a9476
        System.out.println(request.getHeaderNames());//org.apache.tomcat.util.http.NamesEnumerator@4ea09b16
        System.out.println(request.getMethod());//GET
        System.out.println(request.getRequestURL());//http://localhost:8080/req.do
        System.out.println(request.getRequestURI());// /req.do
        System.out.println(request.getHeader("x-requested-with"));//判断是否是异步请求
        System.out.println(request.getQueryString());//获取查询字符串。
//        request.getQueryString()
//        比如客户端发送
//        http://localhost/test.do?a=b&c=d&e=f
//        通过request.getQueryString()得到的是
//                a=b&c=d&e=f
//        API 中注释写到：’
//        意思是：获取带参数查询。
//        post方法传的参数，getQueryString（）得不到，
//        它只对get方法得到的数据有效。

        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String result="";
            String[] value = entry.getValue();
            for (String s : value){
                result += s;
            }
            System.out.println("Key = " + entry.getKey() + ", Value = " + result);
        }

//        request.getParameterMap()返回的是一个Map类型的值，
//        该返回值记录着前端（如jsp页面）所提交请求中的请求参数和请求参数值的映射关系。
//        这个返回值有个特别之处——只能读。



//        response.sendredirect(url);
//        新的页面并不能处理旧页面的pagecontext(request,response,...)对象,
//        所以你用request.getparameter(ff)企图调用原页面
//        request对象的参数，得到的自然是null

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
