package com.oa2.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     *  登录拦截器的前置处理方法，用于判断用户是否已登录
     *  如果 emp 为 null（即未登录），则重定向到登录页面，并返回 false 拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {

        System.out.println("登录拦截器的前置处理方法，用于判断用户是否已登录");
        // 请求上下文路径
        String contextPath = request.getContextPath();
        // 当前正在访问的URL，要传给登录页面，以便登录成功后跳转到该页面，该URL要进行编码处理
        String currUrl = java.net.URLEncoder.encode(request.getServletPath().toString(), "UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("emp") == null) {
            // 拼接登录URL
            String loginUrl = contextPath + "/";
            // 响应重定向到登录URL
            response.sendRedirect(loginUrl);
            // 返回false，说明被拦截
            return false;
        }
        return true;
    }
}
