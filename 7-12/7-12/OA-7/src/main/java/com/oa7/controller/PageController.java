package com.oa7.controller;

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

    //    员工考勤列表页面
    @RequestMapping("/signList")
    public String signList() {
        return "signList";
    }

    //员已签到列表页面
    @RequestMapping("/stuSign")
    public String stuSign() {
        return "stuSign";
    }

    //员工未签到列表页面
    @RequestMapping("/stuNoSign")
    public String stuNoSign() {
        return "stuNoSign";
    }

    //    近五日签到统计图页面
    @RequestMapping("/signImg")
    public String signImg() {
        return "signImg";
    }

}
