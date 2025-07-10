package com.oa4.controller;

import com.oa4.service.AdmService;
import com.oa4.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录与注册控制器
 */

@Controller
public class AdmController {

    @Autowired
    private AdmService admService;

    //管理员登录 路径"login" POST
    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin, HttpSession session) {
        // 调用服务层方法进行登录验证
        Admin loggedInAdmin = admService.login(admin);

        // 如果登录成功，将管理员信息存入会话
        if (loggedInAdmin != null) {
            //需要session，以免全局拦截器拦截
            session.setAttribute("admin", loggedInAdmin);
            return "true";
        } else {
            return "false";
        }
    }

    //管理员注册 路径"register" POST
    @RequestMapping("/register")
    @ResponseBody
    public String register(Admin admin) {
        // 调用服务层方法进行注册
        boolean isRegistered = admService.register(admin);

        // 返回注册结果
        if (isRegistered) {
            return "true";
        } else {
            return "false";
        }

    }



}
