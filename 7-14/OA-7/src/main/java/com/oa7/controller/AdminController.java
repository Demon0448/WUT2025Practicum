package com.oa7.controller;


import com.oa7.pojo.Admin;
import com.oa7.service.AdmService;
import com.oa7.util.RESP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class AdminController {
    //路径 /auth/login POST 参数 name pwd
    @Autowired
    private AdmService admService;

    @RequestMapping("/auth/login")
    @ResponseBody
    public String login(@RequestBody Admin admin, HttpSession session) {
        log.info("登录请求: {}", admin);
        return admService.login(admin, session);
    }

    @PostMapping("/auth/logout")
    public RESP logout(HttpSession session) {
        session.removeAttribute("admin");
        return RESP.ok("退出登录成功");
    }

    //    管理员注册
    @RequestMapping("/auth/register")
    @ResponseBody
    public String register(@RequestBody Admin admin) {
        log.info("注册请求: {}", admin);
        return admService.register(admin);
    }
}
