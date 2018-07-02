package com.yotcap.controller;



import com.yotcap.pojo.Test;
import com.yotcap.result.Result;
import com.yotcap.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

    @Autowired
    private TestService testService;

    @CrossOrigin(origins = "*", maxAge = 60)
    @RequestMapping("/hello.do")
    @ResponseBody
    public Result<Test> helloWorld(){
        Test  test = testService.queryByUserId(1);
        return Result.success(test);
    }
}
