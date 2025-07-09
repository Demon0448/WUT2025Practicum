package com.oa2.controller;

import com.oa2.service.EmpService;
import com.oa2.pojo.Emp;
import com.oa2.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/emplogin")
    @ResponseBody
    public String emplogin(Emp emp, HttpSession session) {
        System.out.println(emp.getNumber()+"  "+emp.getPwd());
        String emplogin = empService.emplogin(emp, session);
        return emplogin;
    }


    @RequestMapping("/getInfo")
    @ResponseBody
    public RESP getInfo(HttpSession session){
        Emp emp = (Emp) session.getAttribute("emp");

        return RESP.ok(emp);
    }


    // 改密码成功后  重新返回到登录页面(向前端响应地址 "/")

    @RequestMapping("/updateEmpPwd")
    @ResponseBody
    public String updateEmpPwd(Emp emp, @RequestParam("oldpwd") String oldpwd){
        return empService.updateEmpPwd(emp,oldpwd);
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public RESP updateInfo(Emp emp, HttpSession session){
        return empService.updateInfo(emp,session);
    }
}
