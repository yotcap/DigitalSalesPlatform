package com.yotcap.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * X-Frame-Options HTTP 响应头是用来给浏览器指示允许一个页面可否在 <frame>, <iframe> 或者 <object> 中展现的标记。
 * 网站可以使用此功能，来确保自己网站的内容没有被嵌到别人的网站中去，也从而避免了点击劫持 (clickjacking) 的攻击。
 * X-Frame-Options 有三个值:
 * 【DENY】表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。
 * 【SAMEORIGIN】表示该页面可以在相同域名页面的 frame 中展示。
 * 【ALLOW-FROM】 uri表示该页面可以在指定来源的 frame 中展示。
 */
public class FrameOptionsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse rsp = (HttpServletResponse)servletResponse;

        //防止网站被人嵌套
        rsp.setHeader("x-frame-options","SAMEORIGIN");//设置响应头，frame页面的地址只能为同源域名下的页面
        HttpServletRequest sreq = (HttpServletRequest) servletRequest;
        String xForwardedProto = sreq.getHeader("x-forwarded-proto");
        if (xForwardedProto != null && xForwardedProto.equals("http")) {
            try {
                URI reqUri = new URI(sreq.getRequestURL().toString());
                URI newUri = new URI("https", reqUri.getAuthority(), reqUri.getPath(), sreq.getQueryString(), reqUri.getFragment());
                rsp.sendRedirect(newUri.toString());
                return;
            } catch (URISyntaxException e) {
                throw new RuntimeException("Illegal URL ", e);
            }
        }

        filterChain.doFilter(servletRequest, rsp);
    }

    @Override
    public void destroy() {

    }
}
