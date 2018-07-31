package com.yotcap.interceptor;

import com.yotcap.annotation.OperationalLog;
import com.yotcap.pojo.Log;
import com.yotcap.pojo.User;
import com.yotcap.result.Const;
import com.yotcap.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OperateLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogService logService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateLogInterceptor.class);
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user != null && (handler instanceof HandlerMethod)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            String uri = request.getRequestURI();
            String query = request.getQueryString();
            Map parameterMap = request.getParameterMap();
            String url = StringUtils.isNotBlank(query) ? String.format("%s?%s", uri, query) : uri;
            String operation = null;
            String desc = null;

            OperationalLog operationalLog = handlerMethod.getMethodAnnotation(OperationalLog.class);
            if (operationalLog != null) {
                if (StringUtils.isEmpty(operation)) {
                    operation = operationalLog.method();
                }

                if (StringUtils.isEmpty(desc)) {
                    desc = operationalLog.value();
                }
            }
            Log log = new Log();
            log.setAccountName(user.getUsername());
            if (url.length() >= 512)
                url = url.substring(0, 512);
            log.setUrl(url);
            log.setOperation(operation);
            log.setDesc(desc);
            log.setResult("1");

            logService.addLog(log);

            System.out.println(log);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
