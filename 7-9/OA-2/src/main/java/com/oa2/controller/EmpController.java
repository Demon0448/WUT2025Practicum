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
        return empService.emplogin(emp , session);
    }


    //获取用户登录后的session信息
    @RequestMapping("/getInfo")
    @ResponseBody
    public RESP getInfo(HttpSession session) {
        Emp emp = (Emp) session.getAttribute("emp");
        return RESP.ok(emp);
    }

    //更新用户密码
    @RequestMapping("/updateEmpPwd")
    @ResponseBody
    public String updateEmpPwd(Emp emp , @RequestParam("oldpwd") String oldpwd) {
        return empService.updateEmpPwd(emp , oldpwd);
    }

    //修改用户信息
    @RequestMapping("/updateInfo")
    @ResponseBody
    public RESP updateInfo(Emp emp,HttpSession session){
        return  empService.updateInfo(emp,session);
    }


}
