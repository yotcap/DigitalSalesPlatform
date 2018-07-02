package com.yotcap.controller;



import com.yotcap.pojo.Test;
import com.yotcap.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello.do")
    @ResponseBody
    public Test helloWorld(){
        System.out.println("ssssss");
         return testService.queryByUserId(1);
    }
}
