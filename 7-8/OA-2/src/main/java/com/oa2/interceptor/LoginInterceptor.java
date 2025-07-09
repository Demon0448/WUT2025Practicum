package com.oa2.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("暂时未登录，登录拦截..");

        String contextPath = request.getContextPath();

        //当前正在访问的URL
        String url=java.net.URLEncoder.encode(request.getServletPath(),"UTF-8");

        HttpSession session=request.getSession();
        if(session.getAttribute("emp")==null){
            String loginUrl=contextPath+"/";//localhost:8080/
            response.sendRedirect(loginUrl);
            return  false;
        }
        return true;
    }
}
