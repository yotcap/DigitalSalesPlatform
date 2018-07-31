package com.yotcap.interceptor;

import com.yotcap.pojo.User;
import com.yotcap.result.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 判断是否登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user==null){
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
