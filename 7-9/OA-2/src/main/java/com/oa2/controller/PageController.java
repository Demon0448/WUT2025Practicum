package com.oa2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    //跳转登陆成功页面
    @RequestMapping("/emploginSuccess")
    public String emploginSuccess() {
        return "emploginSuccess";
    }

    //跳转员工个人中心页面
    @RequestMapping("/empManager")
    public String empManager() {
        return "empManager";
    }

    //跳转员工个人信息列表页面
    @RequestMapping("/empInfoList")
    public String empInfoList() {
        return "empInfoList";
    }

    //跳转员工个更新密码页面
    @RequestMapping("/empUpdatePwd")
    public String empUpdatePwd() {
        return "empUpdatePwd";
    }

    //跳转员工签到页面
    @RequestMapping("/empSignIn")
    public String empSignIn() {
        return "empSignIn";
    }

    //跳转员工签到信息
    @RequestMapping("/empSignMessage")
    public String empSignMessage() {
        return "empSignMessage";
    }
}
