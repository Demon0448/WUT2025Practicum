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

    //用户登录
    @RequestMapping("/emplogin")
    @ResponseBody
    public String emplogin(Emp emp , HttpSession session) {
        System.out.println("用户登录");

        //调用service层的登录方法
        Emp emp1 = empService.login(emp, session);

        //判断登录是否成功
        if (emp1 == null) {
            return "false";
        }
        return "true";
    }




    //修改密码
    @RequestMapping("/updateEmpPwd")
    @ResponseBody
    public String updateEmpPwd(Emp emp,@RequestParam("oldpwd") String oldPwd) {
        return empService.updateEmpPwd(emp, oldPwd);
    }




    //展示个人信息
    @RequestMapping("/getInfo")
    @ResponseBody
    public RESP getInfo(HttpSession session) {
        //调用service层的查询方法
        RESP result = empService.getInfo(session);
        return result;
    }



    //修改个人信息
    @RequestMapping("/updateInfo")
    @ResponseBody
    public RESP updateInfo(Emp emp, HttpSession session) {
        //调用service层的修改方法
        RESP result = empService.updateInfo(emp, session);
        return result;
    }










}
