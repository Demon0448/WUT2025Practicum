package com.oa2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/emploginSuccess")
    public  String emploginSuccess(){
        return "emploginSuccess";
    }

    @RequestMapping("/empManager")
    public  String empManager(){
        return "empManager";
    }

    @RequestMapping("/empInfoList")
    public  String empInfoList(){
        return "empInfoList";
    }

    @RequestMapping("/empUpdatePwd")
    public  String empUpdatePwd(){
        return "empUpdatePwd";
    }

    @RequestMapping("/empSignIn")
    public  String empSignIn(){
        return "empSignIn";
    }

    @RequestMapping("/empSignMessage")
    public  String empSignMessage(){
        return "empSignMessage";
    }











}
