package com.oa2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
    @RequestMapping("/")
    public  String index(){
        return "emplogin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("emp");
        return "redirect:/";

    }
}
