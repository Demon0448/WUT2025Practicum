package com.oa5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

//    所有员工信息页面
    @RequestMapping("/stuList")
    public String stuList() {
        return "stuList";
    }

//管理员首页
    @RequestMapping("/manager")
    public String manager() {
        return "manager";
    }

//    员工人事管理页面
    @RequestMapping("/updateDutyDept")
    public String updateDutyDept() {
        return "updateDutyDept";
    }
//部门管理页面
    @RequestMapping("/deptManage")
    public String deptManage() {
        return "deptManage";
    }
//职务管理页面
    @RequestMapping("/dutyManage")
    public String dutyManage() {
        return "dutyManage";
    }

}
