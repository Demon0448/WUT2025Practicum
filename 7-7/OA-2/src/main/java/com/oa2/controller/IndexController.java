package com.oa2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

    //员工个人中心登录页面--首页
    @RequestMapping("/")
    public String index() {
        return "emplogin";
    }

    //用户退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("emp");
        System.out.println("用户退出登录");
        return "redirect:/";
        
    }


}
