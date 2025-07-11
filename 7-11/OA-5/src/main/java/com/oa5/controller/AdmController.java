package com.oa5.controller;

import com.oa5.service.AdmService;
import com.oa5.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class AdmController {

    @Autowired
    private AdmService admService;

//    管理员登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin, HttpSession session) {
        return admService.login(admin,session);
    }
//    管理员注册
    @RequestMapping("/register")
    @ResponseBody
    public String register(Admin admin) {
        return admService.register(admin);
    }
}
