package com.yotcap.exception;

import com.yotcap.pojo.User;
import com.yotcap.result.CodeMsg;
import com.yotcap.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandle(HttpServletRequest request, Exception e){
//        输出错误信息
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            return Result.error(globalException.getCodeMsg());
        }else {
            return Result.error(CodeMsg.UNKNOWNERROR);
        }

    }

}
