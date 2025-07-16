package com.oa7.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();


        //TODO  openfeign特调部分

        // 从 Cookie 中提取 JSESSIONID
        String jsessionId = null;
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    jsessionId = cookie.getValue();
                    break;
                }
            }
        }

        if (session.getAttribute("admin") == null && jsessionId == null) {
            // 前后端分离架构：返回JSON错误响应而不是重定向
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 401);
            result.put("message", "未登录或登录已过期，请重新登录");
            result.put("data", null);
            
            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
            writer.close();
            
            return false;
        }
        return true;
    }
}
